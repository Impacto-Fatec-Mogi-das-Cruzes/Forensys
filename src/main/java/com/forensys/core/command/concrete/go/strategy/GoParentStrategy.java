package com.forensys.core.command.concrete.go.strategy;

import com.forensys.common.exception.InvalidDirectoryMovement;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.concrete.go.GoExecutionContext;
import com.forensys.core.context.ApplicationContext;

public class GoParentStrategy implements GoStrategy {

	@Override
	public CommandOutput execute() {
		ApplicationContext context = ApplicationContext.getInstance();
		try {
			context.restoreDirectory();
		} catch (InvalidDirectoryMovement e) {
			((GoExecutionContext) context.getExecutionContext()).incrementIndex();
			return CommandOutput.builder()
					.styledText("No parent directory to go back to", "#ef4444")
					.exitCode(CommandExitCode.FAILURE)
					.build();
		} catch (Exception e) {
			context.clearAllExecution();
			return CommandOutput.builder()
					.styledText("Failed to go back to parent directory", "#ef4444")
					.exitCode(CommandExitCode.FAILURE)
					.build();
		}

		((GoExecutionContext) context.getExecutionContext()).incrementIndex();
		return CommandOutput.builder()
				.styledText("Moved to parent directory", "#cbd5e1")
				.exitCode(CommandExitCode.SUCCESS)
				.build();
	}
}