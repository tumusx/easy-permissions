package com.github.tumusx.easy_permissions.ui

import android.graphics.Color
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.View
import com.github.tumusx.easy_permissions.databinding.EasyPermissionsLayoutBinding

fun View.showModalDescriptionPermission(
    titleModal: String,
    descriptionModal: String,
    buttonText: String,
    listener: () -> Unit
) {
    val modalView = EasyPermissionsLayoutBinding.inflate(LayoutInflater.from(context))
    modalView.modalDescription.text = descriptionModal
    modalView.modalTitle.text = titleModal
    modalView.modalConfirmButton.text = buttonText

}