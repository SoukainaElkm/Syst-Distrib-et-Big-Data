import proxy.BanqueServices;
import proxy.BanqueWS;
import proxy.Compte;

public class ClientWS {
    public static void main(String[] args) {
        BanqueServices stub = new BanqueWS().getBanqueServicesPort(); //middleware
        System.out.println(stub.convert(600));
        Compte cp=stub.getCompte(5);
        System.out.println(cp.getCode());
        System.out.println(cp.getSolde());
    }
}
