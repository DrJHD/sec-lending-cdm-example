package org.isla;

import cdm.event.common.ExecutionInstruction;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class TradeUtils {

    private ObjectMapper objectMapper = RosettaObjectMapper.getNewRosettaObjectMapper();

    public ExecutionInstruction createExecutionInstructionFromFile(String filename) throws IOException {
        return objectMapper.readValue(Path.of(filename).toFile(), ExecutionInstruction.class);
    }


    void printJson(Object any) throws JsonProcessingException {
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(any);
        System.out.println(jsonString);
    }

}
