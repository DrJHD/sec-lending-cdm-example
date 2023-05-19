package org.isla;

import cdm.event.common.BusinessEvent;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.Instruction;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.regnosys.rosetta.common.validation.RosettaTypeValidator;
import org.isda.cdm.CdmRuntimeModule;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Injector injector = Guice.createInjector(new CdmRuntimeModule());

        TradeUtils tradeUtils = new TradeUtils();
        ExecutionInstruction executionInstruction = tradeUtils.createExecutionInstructionFromFile("src/main/resources/sec-lending-execution-instruction.json");

        TradeProcessor tradeProcessor = new TradeProcessor();
        injector.injectMembers(tradeProcessor);

        BusinessEvent businessEvent = tradeProcessor.execute(executionInstruction);

        tradeUtils.printJson(businessEvent);

        // Validate the output
        
        RosettaTypeValidator validator = injector.getInstance(RosettaTypeValidator.class);

        validator.runProcessStep(BusinessEvent.class, businessEvent.toBuilder())
                .getValidationResults().forEach(System.out::println);


    }
}
