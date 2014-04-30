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

public class ProjectorAction {
		
		protected String turnOn(String ipAddress) {
			
			String response;
			
			// Power On
			URLReader URLReaderRequest = new URLReader();
			response = URLReaderRequest.Reader("http://" + ipAddress + "/tgi/status.tgi?PowerOn=Power+ON+");
				
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return response;
			
		}
		
		protected String turnOff(String ipAddress) {
			
			String response;

			// Power Off
			URLReader URLReaderRequest = new URLReader();
			response = URLReaderRequest.Reader("http://" + ipAddress + "/tgi/status.tgi?PowerOff=Power+OFF+");
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return response;
			
		}
		
		protected String getStatus(String ipAddress) {
			
			String response;
			
			// Power On
			URLReader URLReaderRequest = new URLReader();
			response = URLReaderRequest.Reader("http://" + ipAddress + "/status.htm");
			
			// parse HTML input
			String[] splitInput = response.split("TYPE=\"text\" VALUE=");
			
			// parse remaining input for status
			String[] splitStatus = splitInput[1].split("\"");
			
			String status = splitStatus[1].replaceFirst(" *$", "");
			
			return status;
			
		}
		
}
