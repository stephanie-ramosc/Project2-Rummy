/********************************************
* File: PlayerWins.java                     *
* Project 2                                 *
* @author Stephanie M. Ramos Camacho        *
* Date: 5/16/2021                           *
* Class Name: CCOM 4029 Spr 2021            *
* E-mail: stephanie.ramoscamacho@upr.edu    *
********************************************/


import javax.swing.JOptionPane;

/*This class will be in charge of spawning a message box that will anounce who won.*/
public class PlayerWins 
{
	 public void infoBox(String WinMessage, String titleBar)
	   {
		 System.out.println(WinMessage); //Prints on console the message of who won.
		 JOptionPane.showMessageDialog(null, WinMessage, titleBar, JOptionPane.INFORMATION_MESSAGE); //Message box that anounces who won.
	   }
}