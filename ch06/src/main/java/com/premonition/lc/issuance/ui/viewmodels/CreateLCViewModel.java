package com.premonition.lc.issuance.ui.viewmodels;

import com.premonition.lc.issuance.domain.LCApplicationId;
import com.premonition.lc.issuance.ui.scopes.LCScope;
import com.premonition.lc.issuance.ui.services.CreateLCService;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@ScopeProvider(LCScope.class)
public class CreateLCViewModel implements de.saxsys.mvvmfx.ViewModel {

    private final int clientReferenceMinLength;

    @InjectScope
    private LCScope lcScope;

    private StringProperty clientReference;
    private BooleanProperty createDisabled;

    @Autowired
    private CreateLCService service;
    private Command createLCCommand;
    private LCApplicationId lcApplicationId;

    public CreateLCViewModel(
                             @Value("${application.client.reference.min.length:4}") int clientReferenceMinLength,
                             CreateLCService service) {
        this.clientReferenceMinLength = clientReferenceMinLength;
        this.service = service;
        this.init();
    }

    public void init() {
        this.clientReference = new SimpleStringProperty(this, "clientReference", "");
        this.createDisabled = new SimpleBooleanProperty(this, "createEnabled");
        this.createDisabled.bind(this.clientReference.length().lessThan(clientReferenceMinLength));
        createLCCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() {
                lcApplicationId = createLC(service);
                lcScope.setLcApplicationId(lcApplicationId);
            }
        });
    }

    public LCApplicationId getLcApplicationId() {
        return lcApplicationId;
    }
    public String getClientReference() {
        return clientReference.get();
    }

    public StringProperty clientReferenceProperty() {
        return clientReference;
    }

    public void setClientReference(String clientReference) {
        this.clientReference.set(clientReference);
    }

    public boolean getCreateDisabled() {
        return createDisabled.get();
    }

    public BooleanProperty createDisabledProperty() {
        return createDisabled;
    }

    public void setCreateDisabled(boolean createDisabled) {
        this.createDisabled.set(createDisabled);
    }

    public LCApplicationId createLC(CreateLCService service) {
        if (!getCreateDisabled()) {
            return service.createLC(this.getClientReference());
        }
        throw new IllegalStateException("Trying to save an invalid LC?");
    }

    public Command getCreateLCCommand() {
        return createLCCommand;
    }

}
