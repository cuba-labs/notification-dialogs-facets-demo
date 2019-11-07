package com.company.sample.web.screens.inputdialog;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.app.core.inputdialog.InputDialog;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.InputDialogFacet;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.stream.Collectors;

@SuppressWarnings({"unused", "InvalidInstalledDelegate"})
@UiController("sample_InputDialogDemo")
@UiDescriptor("input-dialog-demo.xml")
public class InputDialogDemo extends Screen {

    @Inject
    protected InputDialogFacet inputDialogFacet;
    @Inject
    private Notifications notifications;

    @Subscribe("dialogButton")
    public void onDialogButtonClick(Button.ClickEvent event) {
        InputDialog inputDialog = inputDialogFacet.show();
    }

    @Install(to = "inputDialogFacet", subject = "dialogResultHandler")
    public void handleDialogResults(InputDialog.InputDialogResult dialogResult) {
        String closeActionType = dialogResult.getCloseActionType().name();
        String values = dialogResult.getValues().entrySet()
                .stream()
                .map(entry -> String.format("%s = %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));

        notifications.create(Notifications.NotificationType.HUMANIZED)
                .withCaption("InputDialog Result Handler")
                .withDescription("Close Action: " + closeActionType +
                        ". Values: " + values)
                .show();
    }

    @Subscribe("inputDialogFacet")
    public void onInputDialogClose(InputDialogFacet.CloseEvent closeEvent) {
        String values = closeEvent.getValues().entrySet()
                .stream()
                .map(entry -> String.format("%s = %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));

        notifications.create(Notifications.NotificationType.HUMANIZED)
                .withCaption("InputDialog Closed")
                .withDescription("Values: " + values)
                .show();
    }
}