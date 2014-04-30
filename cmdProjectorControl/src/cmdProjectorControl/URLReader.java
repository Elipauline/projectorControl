/**
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions 
 * and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 * and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 
 * Neither the name of Oracle nor the names of its contributors may be used to endorse or promote 
 * products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED 
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A 
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR 
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR 
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package cmdProjectorControl;

import java.net.*;
import java.io.*;

/** Source code for the URLReader class was originally obtained from: 
 *          http://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
 *  and is used under the Code Sample License:
 *  		http://www.oracle.com/technetwork/licenses/bsd-license-1835287.html
 * 
 * Modified by Joel Gibson.
 * 
*/
public class URLReader {
	
    public static void main(String[] args) throws Exception {

        URL oracle = new URL("http://www.oracle.com/");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
    
	public String Reader(String site) {
		  
		try {
		
	        URL siteURL = new URL(site);
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(siteURL.openStream()));

	        String inputLine;
	        String response = "";
	        while ((inputLine = in.readLine()) != null)
	        	response = response + inputLine + "\n";
	            //System.out.println(inputLine);
	        in.close();
	    
	        System.out.println(response);
	        
		    return response;

		} catch (java.net.ConnectException e) {
		      System.out.println("Connection Failed");
		      System.exit(1);
		  
		      
		} catch (Exception e) {
			System.out.println(e.toString());
		    System.exit(1);
		 
		} 
		
		return null;

	        
	}	
    
    
    
}
