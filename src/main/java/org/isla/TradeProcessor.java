package org.isla;

import cdm.event.common.BusinessEvent;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.functions.Create_BusinessEvent;
import com.rosetta.model.lib.records.Date;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TradeProcessor {

    @Inject
    Create_BusinessEvent createBusinessEvent;

    public BusinessEvent execute(ExecutionInstruction executionInstruction) {
        Instruction instruction = createExecutionInstructionFromExecutionInstruction(executionInstruction);
        Date now = Date.of(LocalDate.now());
        //call a function
        BusinessEvent businessEvent = createBusinessEvent.evaluate(
                List.of(instruction),
                null,
                now, now);
        return businessEvent;
    }


    private Instruction createExecutionInstructionFromExecutionInstruction(ExecutionInstruction executionInstruction) {
        return Instruction.builder()
                .setPrimitiveInstruction(createPrimitiveInstruction(executionInstruction));
    }

    private static PrimitiveInstruction.PrimitiveInstructionBuilder createPrimitiveInstruction(ExecutionInstruction executionInstruction) {
        return PrimitiveInstruction.builder().setExecution(executionInstruction);
    }

}
