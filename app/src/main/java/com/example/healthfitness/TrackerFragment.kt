import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.healthfitness.BMIActivity
import com.example.healthfitness.PedoActivity
import com.example.healthfitness.R
import com.example.healthfitness.RunActivity
import com.example.healthfitness.YogaActivity

class TrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find views and set click listeners here
        val bmi: ImageView = view.findViewById(R.id.BMI)
        bmi.setOnClickListener {
            startActivity(Intent(requireContext(), BMIActivity::class.java))
            Toast.makeText(requireContext(), "BMI selected", Toast.LENGTH_SHORT).show()
        }

        val pedo: ImageView = view.findViewById(R.id.Pedometer)
        pedo.setOnClickListener {
            startActivity(Intent(requireContext(), PedoActivity::class.java))
            Toast.makeText(requireContext(), "Pedometer selected", Toast.LENGTH_SHORT).show()
        }

        val run: ImageView = view.findViewById(R.id.Running)
        run.setOnClickListener {
            startActivity(Intent(requireContext(), RunActivity::class.java))
            Toast.makeText(requireContext(), "Running tracker selected", Toast.LENGTH_SHORT).show()
        }

        val yoga: ImageView = view.findViewById(R.id.Yoga)
        yoga.setOnClickListener {
            startActivity(Intent(requireContext(), YogaActivity::class.java))
            Toast.makeText(requireContext(), "Yoga selected", Toast.LENGTH_SHORT).show()
        }
    }
}
