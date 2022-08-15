package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.TicketService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    //Variables required to calculate the total price and no. of Seats
    static int totalNoOfTickets,totalAmountToPay,totalNoOfSeats;
    static int totalNoOfAdultTickets, totalNoOfChildren, totalNoOfInfants;
    static boolean validTicket;

    /**
    *   validateRequest method validates if the ticket is a valid request
    *   It returns True if 
    *               Atleast one adult ticket
    *               and total number of tickets <=20
    *               and No. of infants <= no. of adults
    *   Returns false for all other conditions and throws InvalidPurchaseException
    */
    private boolean validateRequest(){
        if (totalNoOfTickets >=1 && totalNoOfTickets <=20){
              if (totalNoOfInfants <= totalNoOfAdultTickets){
                        validTicket = true;
              }else{
                    validTicket = false;
              }
         }else{
                    validTicket = false;
         }
        return validTicket;
    }

     /**
     * initializeValues method initializes all the values
     *  from the varargs argument passed
     */
    private void initializeValues(TicketTypeRequest...objTicketTypeRequest){
        totalNoOfTickets = 0;
        totalAmountToPay = 0;
        totalNoOfSeats = 0;
        totalNoOfAdultTickets = 0;
        totalNoOfChildren = 0;
        totalNoOfInfants = 0;
        int noOfTickets = 0;
        for (TicketTypeRequest ticketRequest : objTicketTypeRequest) {
            noOfTickets = ticketRequest.getNoOfTickets();
            switch(ticketRequest.getTicketType()) {
                case ADULT:
                totalNoOfAdultTickets=noOfTickets;
                totalNoOfTickets+=noOfTickets;
                break;
                case CHILD:
                totalNoOfChildren=noOfTickets;
                totalNoOfTickets+=noOfTickets;
                break;
                case INFANT:
                totalNoOfInfants=noOfTickets;
                totalNoOfTickets+=noOfTickets;
                break;
                }    
         }
    }


    /**
    *   calculateNoOfSeats method calculates the no.of seats required to reserve.
    *   Infants don't get a seat and hence not included in the final count
    */
    static void calculateNoOfSeats(){
          totalNoOfSeats= totalNoOfAdultTickets + totalNoOfChildren;
    }



    /**
    *   calculatePrice method calculates the total price
    *   Infants go free and hence not included in the final price
    *   Prices calculated at per the table below
    * IF price changes, variables adultPricePerTicket, childPricePerTicket change
    */
    static void calculatePrice(){
      /*|  Ticket Type    |    Price    |
        | ----------------| ----------- |
        |    INFANT       |    0        |
        |    CHILD        |    10 GBP   |
        |    ADULT        |    20 GBP   | */
           int adultPricePerTicket = 20;
           int childPricePerTicket = 10;
           int totalAdultPrice = 0;
           int totalChildPrice = 0;
           totalAmountToPay = 0;
           totalAdultPrice = totalNoOfAdultTickets * adultPricePerTicket;
           totalChildPrice = totalNoOfChildren * childPricePerTicket;
           totalAmountToPay = totalAdultPrice + totalChildPrice;
    }
    
    private boolean checkAccountID(Long accID){
        boolean boolcheckAccountID = false;
        if (accID > 0) boolcheckAccountID = true;
        return boolcheckAccountID;
    }

    //@Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        try
        {
            if(checkAccountID(accountId))
            {
                initializeValues(ticketTypeRequests);
                    if (validateRequest())
                    {
                        calculateNoOfSeats();
                        //System.out.println("Total no of seats  "+totalNoOfSeats);
                        calculatePrice();
                        //System.out.println("Total price to pay  "+totalAmountToPay);
                    }else{
                        throw new Exception();
                    }
            }else{
                        throw new Exception();
                    }
        }
        catch(Exception e)
        {
            System.out.println("Invalid account");
        }


    }
 }
