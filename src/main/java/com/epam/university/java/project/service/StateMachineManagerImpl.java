package com.epam.university.java.project.service;

import com.epam.university.java.project.core.cdi.io.Resource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;
import com.epam.university.java.project.core.state.machine.domain.StateMachineEventHandler;
import com.epam.university.java.project.core.state.machine.domain.StateMachineState;
import com.epam.university.java.project.core.state.machine.domain.StateMachineStateImpl;
import com.epam.university.java.project.core.state.machine.domain.StatefulEntity;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.lang.reflect.Method;

public class StateMachineManagerImpl implements StateMachineManager {

    /**
     * Read state machine definition from resource.
     *
     * @param resource resource with definition
     * @return state machine definition
     */
    @Override
    public StateMachineDefinition<?, ?> loadDefinition(Resource resource) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(
                    StateMachineDefinitionImpl.class,
                    StateMachineStateImpl.class
            );
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return (StateMachineDefinitionImpl) unmarshaller.unmarshal(resource.getFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Init new state machine in accordance with definition.
     *
     * @param entity     entity to create state
     * @param definition state machine definition
     * @return initialized stateful entity
     */
    @Override
    public <S, E> StatefulEntity<S, E> initStateMachine(StatefulEntity<S, E> entity,
                                                        StateMachineDefinition<S, E> definition) {
        entity.setStateMachineDefinition(definition);
        return entity;
    }

    /**
     * Handle event for stateful entity with event handler.
     *
     * @param entity entity to update
     * @param event  event to handle
     * @return updated entity
     */
    @Override
    public <S, E> StatefulEntity<S, E> handleEvent(StatefulEntity<S, E> entity, E event) {
        try {
            StateMachineDefinition<S, E> stateMachineDefinition =
                    entity.getStateMachineDefinition();

            StateMachineEventHandler handler = stateMachineDefinition.getHandlerClass()
                    .getDeclaredConstructor().newInstance();

            StateMachineState stateMachineState = null;
            for (StateMachineState state : stateMachineDefinition.getStates()) {
                if (state.getFrom().equals(entity.getState())
                        && state.getOn().equals(event)) {
                    stateMachineState = state;
                    break;
                }
            }

            if (stateMachineState != null) {
                String methodToCall = stateMachineState.getMethodToCall();
                Method[] declaredMethods = handler.getClass().getDeclaredMethods();

                for (Method method : declaredMethods) {
                    if (method.getName().equals(methodToCall)) {
                        entity = (StatefulEntity) method.invoke(handler, entity);
                        break;
                    }
                }
            }

            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
