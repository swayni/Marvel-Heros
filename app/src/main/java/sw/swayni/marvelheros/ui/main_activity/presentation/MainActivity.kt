package sw.swayni.marvelheros.ui.main_activity.presentation


import dagger.hilt.android.AndroidEntryPoint
import sw.swayni.base.mvvm.view.BindingActivity
import sw.swayni.marvelheros.ui.main_activity.viewmodel.MainViewModel
import sw.swayni.marvelheros.R
import sw.swayni.marvelheros.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main, MainViewModel::class.java) {


    override fun init() {

    }

    override fun observeData() {

    }
}