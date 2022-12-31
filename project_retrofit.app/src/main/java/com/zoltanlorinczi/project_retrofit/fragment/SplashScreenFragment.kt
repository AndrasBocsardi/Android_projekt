import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModelFactory

class SplashFragment : Fragment() {
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(ThreeTrackerRepository())
        profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("SplashFragment", "onCreateView called splash")
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        profileViewModel.isLoggedIn.observe(viewLifecycleOwner){
            if (it==true){
                Thread.sleep(2000)
                findNavController().navigate(R.id.listFragment)
            }
            else
            {
                Thread.sleep(2000)
                findNavController().navigate(R.id.loginFragment)
            }
        }
        return view
    }
}