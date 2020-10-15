package example;

import org.libra.*;
import org.libra.jsonrpc.LibraJsonRpcClient;
import org.libra.jsonrpctypes.JsonRpc.Account;
import org.libra.types.AccountAddress;
import org.libra.utils.CurrencyCode;

import static org.libra.Testnet.CHAIN_ID;
import static org.libra.Testnet.JSON_RPC_URL;

/**
 * GetAccountInfoExample demonstrates the required operation to retrieve account information from the Libra blockchain
 */
public class GetAccountInfoExample {
    public static void main(String[] args) {
        //create account
        PrivateKey privateKey = GenerateKeysExample.generatePrivateKey();
        AuthKey authKey = GenerateKeysExample.generateAuthKey(privateKey);
        MintExample.mint(authKey, 1340000000, "Coin1");

        //get account information
        Account account = getAccountInfo(authKey.accountAddress());

        System.out.println("~ Account info:");
        System.out.println(account);
    }

    public static Account getAccountInfo(AccountAddress accountAddress) {
        //Connect to testnet
        LibraClient client = new LibraJsonRpcClient(JSON_RPC_URL, CHAIN_ID);

        try {
            return client.getAccount(accountAddress);
        } catch (LibraException e) {
            throw new RuntimeException(e);
        }
    }
}
