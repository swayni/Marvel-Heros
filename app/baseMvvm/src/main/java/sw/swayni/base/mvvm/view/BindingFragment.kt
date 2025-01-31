package sw.swayni.base.mvvm.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import sw.swayni.base.mvvm.R
import sw.swayni.base.mvvm.annotation.BindingOnly
import sw.swayni.base.mvvm.core.observe
import sw.swayni.base.mvvm.enums.NavigationAction
import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.viewmodel.BaseViewModel

abstract class BindingFragment <B: ViewDataBinding, VM: ViewModel> constructor(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass:  Class<VM>
) : Fragment(){
    /** This interface is generated during compilation to contain getters for all used instance `BindingAdapters`. */
    private var bindingComponent: DataBindingComponent? = DataBindingUtil.getDefaultComponent()

    /** A backing field for providing an immutable [binding] property.  */
    private var _binding: B? = null
    private var loadingDialog : Dialog? = null

    protected val viewModel : VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    /**
     * A data-binding property will be initialized in [onCreateView].
     * And provide the inflated view which depends on [layoutId].
     */
    @BindingOnly
    protected val binding: B
        get() = checkNotNull(_binding) {
            "Fragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
        }

    /**
     * An executable inline binding function that receives a binding receiver in lambda.
     *
     * @param block A lambda block will be executed with the binding receiver.
     * @return T A generic class that extends [ViewDataBinding] and generated by DataBinding on compile time.
     */
    @BindingOnly
    protected inline fun binding(block: B.() -> Unit): B {
        return binding.apply(block)
    }

    /**
     * Ensures the [binding] property should be executed and provide the inflated view which depends on [layoutId].
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        createdObserve()
        observeNavigation()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false, bindingComponent)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeData()
    }

    abstract fun initUI()
    abstract fun observeData()
    abstract fun createdObserve()

    /**
     * Destroys the [_binding] backing property for preventing leaking the [ViewDataBinding] that references the Context.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        loadingDialog = null
        _binding?.unbind()
        _binding = null
    }

    protected fun showLoading(status: UiState?) {
        if (loadingDialog == null && context != null){
            loadingDialog = Dialog(requireContext())
        }
        if (context != null) {
            loadingDialog?.let { loadingDialog->
                loadingDialog.setContentView(R.layout.layout_loading)
                loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                loadingDialog.setCanceledOnTouchOutside(false)
                if (status == UiState.LOADING) {
                    if (!loadingDialog.isShowing) {
                        loadingDialog.show()
                    }
                } else {
                    loadingDialog.dismiss()
                }
            }
        }
    }


    private fun observeNavigation() {
        when (viewModel) {
            is BaseViewModel<*, *> -> {

                val baseViewModel = viewModel as BaseViewModel<*, *>

                observe(baseViewModel.navigation) {
                    it.getContentIfNotHandled()?.let { navAction ->
                        handleNavigation(navAction = navAction)
                    }
                }
            }
        }
    }

    private fun handleNavigation(navAction: NavigationAction) {
        when (navAction) {
            is NavigationAction.ToDirection -> navigateSafe(findNavController(), navAction.direction)
            NavigationAction.Back -> findNavController().navigateUp()
        }
    }

    open fun navigateSafe(navController: NavController, direction: NavDirections) {
        val currentDestination = navController.currentDestination
        if (currentDestination != null) {
            val navAction = currentDestination.getAction(direction.actionId)
            if (navAction != null) {
                val destinationId: Int = orEmpty(navAction.destinationId)
                val currentNode: NavGraph? = if (currentDestination is NavGraph) currentDestination else currentDestination.parent
                if (destinationId != 0 && currentNode != null && currentNode.findNode(destinationId) != null) {
                    navController.navigate(direction)
                }
            }
        }
    }

    open fun navigateSafe(navController: NavController, @IdRes resId: Int, args: Bundle?) {
        val currentDestination = navController.currentDestination
        if (currentDestination != null) {
            val navAction = currentDestination.getAction(resId)
            if (navAction != null) {
                val destinationId = orEmpty(navAction.destinationId)
                val currentNode: NavGraph? = if (currentDestination is NavGraph) currentDestination else currentDestination.parent
                if (destinationId != 0 && currentNode != null && currentNode.findNode(destinationId) != null) {
                    navController.navigate(resId, args)
                }
            }
        }
    }

    private fun orEmpty(value: Int?): Int {
        return value ?: 0
    }
}