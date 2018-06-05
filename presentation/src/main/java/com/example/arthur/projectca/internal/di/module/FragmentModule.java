package com.example.arthur.projectca.internal.di.module;

import com.example.arthur.projectca.internal.di.scope.PerFragment;
import com.example.arthur.projectca.view.fragment.BaseFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private final BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }
    /**
     * Expose the fragment to dependents in the graph.
     */
    @Provides
    @PerFragment
    BaseFragment provideFragment() {
        return fragment;
    }
}
