package com.gitlab.pedrioko.ats.vaadin.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) throws ValidationException {

        Button save = new Button("Save");
        FormLayout layoutWithBinder = new FormLayout();
        Binder binder = new Binder<>(Test.class);
        List<Field> fields = Arrays.asList(Test.class.getDeclaredFields());
        Test bean1 = new Test();
        binder.readBean(bean1);

        fields.forEach(e -> {
            TextField txt = new TextField();
            txt.setPlaceholder(e.getName());
            txt.setValueChangeMode(ValueChangeMode.EAGER);
            layoutWithBinder.addFormItem(txt, e.getName());
            binder.forField(txt).bind((a) ->
                            ReflectionJavaUtil.getValueFieldObject(e.getName(), a)
                    , (a, x) ->
                            ReflectionJavaUtil.setValueFieldObject(e.getName(), a, x)
                    );
        });

        Button button = new Button("Click me",
                e -> {
                    try {
                        binder.writeBean(bean1);
                        System.out.println(bean1);
                    } catch (ValidationException e1) {
                        e1.printStackTrace();
                    }
                });
        HorizontalLayout actions = new HorizontalLayout();
        actions.add(button);
        add(layoutWithBinder);
        add(actions);
    }

}