package com.forensys.core.command.concrete.go.strategy;

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
		} catch (Exception e) {
			context.clearAllExecution();
			return CommandOutput.builder()
					.text("Failed to go back to parent directory", "#ef4444")
					.exitCode(CommandExitCode.FAILURE)
					.build();
		}

		((GoExecutionContext) context.getExecutionContext()).incrementIndex();
		return CommandOutput.builder()
				.text("Moved to parent directory", "#cbd5e1")
				.exitCode(CommandExitCode.SUCCESS)
				.build();
	}
}