package org.care.packie.feature.stuffs

import dagger.hilt.android.lifecycle.HiltViewModel
import org.care.packie.domain.StuffsRepository
import javax.inject.Inject

@HiltViewModel
class StuffsViewModel @Inject constructor(
    private val stuffsRepository: StuffsRepository
) {

}