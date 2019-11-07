package com.company.sample.web.screens.notification;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.NotificationFacet;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.function.Supplier;

@SuppressWarnings({"unused", "InvalidInstalledDelegate"})
@UiController("sample_NotificationFacetDemo")
@UiDescriptor("notification-facet-demo.xml")
public class NotificationFacetDemo extends Screen {

    @Inject
    protected NotificationFacet notification;
    @Inject
    protected Notifications notifications;

    /**
     * Invoke {@link NotificationFacet#show()} explicitly to show notification.
     */
    @Subscribe("notificationExplicit")
    public void onNotificationExplicitClick(Button.ClickEvent event) {
        notification.show();
    }

    /**
     * Subscribe for {@link NotificationFacet.CloseEvent}.
     */
    @Subscribe("notification")
    protected void onNotificationClose(NotificationFacet.CloseEvent closeEvent) {
        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption("Notification close event listener triggered")
                .show();
    }

    /**
     * Provide notification caption via {@link NotificationFacet#setCaptionProvider(Supplier)}.
     */
    @Install(to = "notification", subject = "captionProvider")
    protected String getNotificationCaption() {
        return "Caption provided from @Install delegate";
    }

    /**
     * Provide notification description via {@link NotificationFacet#setDescriptionProvider(Supplier)}
     */
    @Install(to = "notification", subject = "descriptionProvider")
    protected String getNotificationDescription() {
        return "Description provided from @Install delegate";
    }
}