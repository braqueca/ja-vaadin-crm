package gt.edu.umg.crm.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import gt.edu.umg.crm.backend.entity.Company;
import gt.edu.umg.crm.backend.entity.Contact;
import gt.edu.umg.crm.backend.service.ContactService;


@Route(value = "", layout = MainLayout.class)
@PageTitle("Contactos | Vaadin Ejemplo")
public class ListView extends VerticalLayout {

    private ContactService contactService;
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();

    public ListView(ContactService contactService) {
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();


        Div content = new Div(grid);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(),content);
        updateList();

    }

    private void configureGrid(){
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("company");
        grid.setColumns("firstName", "lastName", "email", "status");
        grid.addColumn(contact -> {
            Company company = contact.getCompany();
            return company == null ? "-" : company.getName();
        }).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private void updateList(){
        grid.setItems(contactService.findAll(filterText.getValue()));
    }

    private Component getToolBar(){
        filterText.setPlaceholder("Nombre");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        HorizontalLayout toolBar = new HorizontalLayout(filterText);
        return toolBar;
    }

}
