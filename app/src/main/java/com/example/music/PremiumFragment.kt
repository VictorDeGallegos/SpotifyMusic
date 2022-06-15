package com.example.music

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.music.databinding.FragmentPremiumBinding
import com.example.music.firebase.user.UserInfo
import com.example.music.util.Constants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PremiumFragment : Fragment() {
    private var _binding: FragmentPremiumBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPremiumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences(
            Constants.PREF_KEY, Context.MODE_PRIVATE)
        val userid = sharedPref.getString(Constants.PREF_USERID, "")
        val db = Firebase.firestore // inicialización de DB
        db.collection(Constants.FIRESTORE_KEY_USER).get()
            .addOnSuccessListener { documentList ->
                if (documentList != null && !documentList.isEmpty){ //mandando a llamar usuarios
                    val userList = documentList.toObjects(UserInfo::class.java)
                    val user = userList.firstOrNull() { it.authId == userid }
                    showUserData(user)
                }
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }

    private fun showUserData(user: UserInfo?) {
        user?.let {
            binding.premiumNombretv.text=it.name
            binding.premiumEmailtv.text=it.email
            
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}