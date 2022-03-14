package kg.geektech.weatherapp30.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import kg.geektech.weatherapp30.R;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    protected VB binding;
    protected NavController controller;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = bind();
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host_xml);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        initViewModel();
        callRequest();
        setupListeners();
        setupObverse();
    }

    protected abstract void callRequest();

    protected abstract void setupObverse();

    protected abstract void setupListeners();

    protected abstract void initViewModel();

    protected abstract void setupViews();

    protected abstract VB bind();
}