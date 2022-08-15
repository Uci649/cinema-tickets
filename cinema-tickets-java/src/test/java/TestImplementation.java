import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.*;

    public class TestImplementation {
        public static void main(String[] args) throws Exception{
        long accID = 100;
        TicketTypeRequest[] obj = new TicketTypeRequest[3];
        obj[0] = new TicketTypeRequest(TicketTypeRequest.Type.ADULT,2);
        obj[1] = new TicketTypeRequest(TicketTypeRequest.Type.CHILD,2);
        obj[2] = new TicketTypeRequest(TicketTypeRequest.Type.INFANT,0);
        TicketServiceImpl objTicketServiceImpl = new TicketServiceImpl();
        //System.out.print("testing debug window");
        objTicketServiceImpl.purchaseTickets(accID,obj);
        //System.out.print("Finished");
    }
    }