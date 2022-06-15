package com.example.music.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.music.albums.AlbumItem
import com.example.music.albums.AlbumModel
import com.example.music.databinding.FragmentHomeBinding
import com.example.music.util.Constants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.GroupieAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private var contentAdapter: GroupieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchAlbums()
    }

    private fun fetchAlbums() {
        contentAdapter = GroupieAdapter()
        binding.recyclerTrendingAlbums.adapter=contentAdapter
        binding.recyclerTrendingAlbums.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        val db = Firebase.firestore // inicialización de DB
        db.collection(Constants.FIRESTORE_KEY_ALMBUM).get()
            .addOnSuccessListener { albumList ->
                if (albumList != null && !albumList.isEmpty) { //mandando a llamar albums
                    val albums = albumList.toObjects(AlbumModel::class.java)
                    showAlbums(albums)
                }
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }

    private fun showAlbums(albums: List<AlbumModel>) {
        albums.forEach { albumInfo ->
            contentAdapter?.add(AlbumItem(albumInfo))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}