package com.company.sample.web.screens.optiondialog;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.ActionsAwareDialogFacet.DialogActionPerformedEvent;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.OptionDialogFacet;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@SuppressWarnings({"unused", "InvalidInstalledDelegate"})
@UiController("sample_OptionDialogDemo")
@UiDescriptor("option-dialog-demo.xml")
public class OptionDialogDemo extends Screen {

    @Inject
    protected OptionDialogFacet optionDialog;
    @Inject
    protected Notifications notifications;

    @Install(to = "optionDialog.ok", subject = "actionHandler")
    protected void onDialogOkAction(DialogActionPerformedEvent<OptionDialogFacet> event) {
        String actionId = event.getDialogAction().getId();

        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption("Dialog action performed: " + actionId)
                .show();
    }

    @Install(to = "optionDialog.cancel", subject = "actionHandler")
    protected void onDialogCancelAction(DialogActionPerformedEvent<OptionDialogFacet> event) {
        String actionId = event.getDialogAction().getId();

        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption("Dialog action performed: " + actionId)
                .show();
    }

    @Subscribe("explicitDialogShowBtn")
    public void onExplicitDialogShowBtnClick(Button.ClickEvent event) {
        optionDialog.show();
    }
}