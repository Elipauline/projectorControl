/**
 * 
 * Copyright (c) 2014, Joel Gibson
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package cmdProjectorControl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to send limited actions to a Dell 1610HD projector from the command line.
 * 
 */
public class cmdProjectorControl {

	String validActions = "(?i)on|off|status";
	double version = 0.3;
		
	/** IP address regular expression checker obtained from:
	 * https://community.oracle.com/thread/1357517?tstart=18675
	 * 
	 */
	static Pattern pat = Pattern.compile("\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");
	static boolean checkString(String s) {
        Matcher m = pat.matcher(s);
        return m.matches();
    }
	
	private void printSyntax(String error) {
		System.out.println("cmdProjectControl Syntax: [action] [ip_address] [optional_timeout_value_seconds]\n\nPossible Actions: on, off, status\n\n"
				+ "Examples:\n\njava -jar cmdProjectorControl-" + version + ".jar on 192.168.2.20\njava -jar cmdProjectorControl-" + version + ".jar "
				+ "off 192.168.2.20 15\njava -jar cmdProjectorControl-" + version + ".jar status 192.168.2.20");
		
		System.out.println("\nError: " + error);
		
		System.exit(1);	// abnormal termination
		
	}
	
	private void testArguments(String[] args) {
		
		// test number of arguments
		if (args.length <= 1 && args.length >= 4)
			printSyntax("Invalid Number of Arguments");
			
		// test for valid actions
		if (!args[0].matches(validActions))
			printSyntax("Invalid Action");
		
		// test for IP address
		if (!checkString(args[1]))
			printSyntax("Invalid IP Address");
		
		// test for timeout value (if present)
		if (args.length == 3) {
			if (Integer.parseInt(args[2]) <= 0 || Integer.parseInt(args[2]) > 120)
				printSyntax("Invalid Timeout Value (Required: 1-120)");
		}
					
	} // end of testArguments
	
	private String handleCommand(String action, String ip, int timeout) {
		
		if (action.matches("(?i)on")) {
			//System.out.println("ON");
			ProjectorAction pAction = new ProjectorAction();
			pAction.turnOn(ip, timeout);
			return pAction.getStatus(ip, timeout);
			
		} else if (action.matches("(?i)off")) {
			//System.out.println("OFF");
			ProjectorAction pAction = new ProjectorAction();
			pAction.turnOff(ip, timeout);
			return pAction.getStatus(ip, timeout);
			
		} else if (action.matches("(?i)status")) {
			//System.out.println("STATUS");
			ProjectorAction pAction = new ProjectorAction();
			return pAction.getStatus(ip, timeout);
			
		} else {
			
			printSyntax("ERROR");
			return null;
			
		}
			
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// test arguments
		new cmdProjectorControl().testArguments(args);
		
		String action = args[0];
		String ip = args[1];
		
		// define a default timeout value
		int timeout = 15000;
		
		if (args.length == 3)
			timeout = Integer.parseInt(args[2]) * 1000;
				
		// handle command
		System.out.println(new cmdProjectorControl().handleCommand(action, ip, timeout));
		
	}

}
