package com.example.myapptabbed.ui.dashboard

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapptabbed.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

	private var _binding: FragmentDashboardBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val dashboardViewModel =
			ViewModelProvider(this).get(DashboardViewModel::class.java)

		_binding = FragmentDashboardBinding.inflate(inflater, container, false)
		val root: View = binding.root

		val textView: TextView = binding.textDashboard
		dashboardViewModel.text.observe(viewLifecycleOwner) {
			textView.text = it
		}

		val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
		val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
		if (bluetoothAdapter == null) {
			// Device doesn't support Bluetooth

		}
		else{
			if (bluetoothAdapter?.isEnabled == false) {
				val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
				startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
			}
		}





		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}