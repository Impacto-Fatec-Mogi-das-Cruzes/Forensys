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
					.text(e.getMessage())
					.exitCode(CommandExitCode.FAILURE)
					.build();
		} catch (Exception e) {
			return CommandOutput.builder()
					.text("UnknownError")
					.exitCode(CommandExitCode.FAILURE)
					.build();
		}

		return CommandOutput.builder()
				.text("Moved to parent directory")
				.exitCode(CommandExitCode.SUCCESS)
				.build();
	}
}