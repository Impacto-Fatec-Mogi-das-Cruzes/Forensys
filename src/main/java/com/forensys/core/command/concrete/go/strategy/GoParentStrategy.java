package com.forensys.core.command.concrete.go.strategy;

import com.forensys.common.exception.InvalidDirectoryMovement;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.context.ApplicationContext;

public class GoParentStrategy implements GoStrategy {

	@Override
	public CommandOutput execute() {
		try {
			ApplicationContext.getInstance().restoreDirectory();
		} catch (InvalidDirectoryMovement e) {
			return CommandOutput.builder()
					.styledText(e.getMessage(), "#ef4444")
					.exitCode(CommandExitCode.FAILURE)
					.build();
		} catch (Exception e) {
			return CommandOutput.builder()
					.styledText("Failed to go back to parent directory", "#ef4444")
					.exitCode(CommandExitCode.FAILURE)
					.build();
		}

		return CommandOutput.builder()
				.styledText("Moved to parent directory", "#cbd5e1")
				.exitCode(CommandExitCode.SUCCESS)
				.build();
	}
}