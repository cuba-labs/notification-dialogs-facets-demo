package com.company.sample.web.screens.messagedialog;

import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.MessageDialogFacet;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("sample_MessageDialogDemo")
@UiDescriptor("message-dialog-demo.xml")
public class MessageDialogDemo extends Screen {

    @Inject
    protected MessageDialogFacet messageDialog;

    @Subscribe("explicitDialogShow")
    public void onExplicitDialogShowClick(Button.ClickEvent event) {
        messageDialog.show();
    }
}