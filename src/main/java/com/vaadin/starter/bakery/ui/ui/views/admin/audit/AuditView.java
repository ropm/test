package com.vaadin.starter.bakery.ui.views.admin.audit;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.starter.bakery.backend.data.AuditLogItem;
import com.vaadin.starter.bakery.backend.service.AuditLogService;
import com.vaadin.starter.bakery.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuditView extends Composite<VerticalLayout> implements HasComponents{

    private UserService userService;

    @Autowired
    public AuditView(UserService userService){
        getContent().setSpacing(true);
        getContent().setMargin(true);
        this.userService = userService;

        ListDataProvider<AuditLogItem> dataProvider = DataProvider.ofCollection(AuditLogService.getAuditLogItems());

        ComboBox<String> userFilter = new ComboBox<>();
        userFilter.setLabel("Filter by user name:");
        Collection<String> userNames = userService.getRepository().findAll().stream().map(user->user.getEmail()).collect(Collectors.toList());
        userFilter.setItems(userNames);
        userFilter.addValueChangeListener(e->{
            dataProvider.clearFilters();
            Optional.ofNullable(e.getValue()).ifPresent(userName ->dataProvider.setFilter(item->e.getValue().equals(item.getUserName())));
        });

        add(userFilter);


        Grid<AuditLogItem> logItemsGrid = new Grid<>();
        logItemsGrid.setDataProvider(dataProvider);
        logItemsGrid.addColumn(new LocalDateTimeRenderer<>(AuditLogItem::getLocalDateTime)).setHeader("Time");
        logItemsGrid.addColumn(AuditLogItem::getUserName).setHeader("User Name");
        logItemsGrid.addColumn(AuditLogItem::getMessage).setHeader("Message").setFlexGrow(3);
        add(logItemsGrid);

        getContent().setFlexGrow(1, logItemsGrid);

    }
}
