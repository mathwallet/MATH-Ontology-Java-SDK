package com.xiaofei.ontologyandroidsdkuse;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Base64;

import com.alibaba.fastjson.JSONObject;
import com.github.ontio.OntSdk;
import com.github.ontio.common.Common;
import com.github.ontio.common.Helper;
import com.github.ontio.core.governance.VoteInfo;
import com.github.ontio.core.ontid.Attribute;
import com.github.ontio.crypto.Digest;
import com.github.ontio.crypto.SignatureScheme;
import com.github.ontio.sdk.exception.SDKException;
import com.github.ontio.sdk.manager.ConnectMgr;
import com.github.ontio.sdk.manager.WalletMgr;
import com.github.ontio.sdk.wallet.Account;
import com.github.ontio.sdk.wallet.Identity;
import com.github.ontio.sdk.wallet.Wallet;
import com.github.ontio.smartcontract.nativevm.Ong;
import com.github.ontio.smartcontract.nativevm.Ont;
import com.github.ontio.smartcontract.nativevm.OntId;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class AssetTest {
    private OntSdk ontSdk;
    private ConnectMgr connectMgr;
    private Ont ont;
    private Ong ong;
    private WalletMgr walletMgr;
    private Wallet wallet;
    private Context appContext;
    private OntId ontIdTx;
    String password = "111111";
    public static String privatekey1 = "75de8489fcb2dcaf2ef3cd607feffde18789de7da129b5e97c81e001793cb7cf";


    @Before
    public void setUp() throws Exception {
        ontSdk = OntSdk.getInstance();
//        ontSdk.setRestful("http://polaris1.ont.io:20334");
        ontSdk.setRestful("http://139.219.128.60:20334");
//        ontSdk.setRestful("http://192.168.50.74:20334");
        appContext  = InstrumentationRegistry.getTargetContext();
        ontSdk.openWalletFile(appContext.getSharedPreferences("wallet",Context.MODE_PRIVATE));
        walletMgr = ontSdk.getWalletMgr();
        wallet = walletMgr.getWallet();
        connectMgr = ontSdk.getConnect();
        ont = ontSdk.nativevm().ont();
        ontIdTx = ontSdk.nativevm().ontId();
    }

    @After
    public void tearDown() throws Exception {



    }

    @Test
    public void ongTest() throws Exception {
//        ontSdk.getWalletMgr().importIdentity("dRiHlKa16kKGuWEYWhXUxvHcPlLiJcorAN3ocZ9fQ8p832p4OdIIiy+kR6eImjYd","111111", Base64.decode("sJwpxe1zDsBt9hI2iA2zKQ==",Base64.NO_WRAP),"AakBoSAJapitE4sMPmW7bs8tfT4YqPeZEU");
        Account account99 = ontSdk.getWalletMgr().createAccountFromPriKey("111111","6717c0df45159d5b5ef383521e5d8ed8857a02cdbbfdefeeeb624f9418b0895e");
        int aa = 0;
        String prikey = "75de8489fcb2dcaf2ef3cd607feffde18789de7da129b5e97c81e001793cb7cf";
        com.github.ontio.account.Account account2 = new com.github.ontio.account.Account(Helper.hexToBytes(prikey),SignatureScheme.SHA256WITHECDSA);
        Account account = ontSdk.getWalletMgr().importAccount("C37723zCK+H7oFQEeyYK+DDWD3o7H5oIAK5uMnKsHtpZ2365b8zen4fGapJLjaOr","11111111","AUDDJAsG1nQo115gXh8rtfocgfT2QLqLDp",Base64.decode("qWmSyQtulLImm5WrmqEBzw==",Base64.NO_WRAP));
        int a1a = 0;

        com.github.ontio.account.Account account1 = ontSdk.getWalletMgr().getAccount("AUDDJAsG1nQo115gXh8rtfocgfT2QLqLDp","11111111",Base64.decode("qWmSyQtulLImm5WrmqEBzw==",Base64.NO_WRAP));
        System.out.println("account1:" + ontSdk.getConnect().getBalance(account1.getAddressU160().toBase58()));
        System.out.println("account2:" + ontSdk.getConnect().getBalance(account2.getAddressU160().toBase58()));
        if(false){
            ontSdk.nativevm().ong().sendTransfer(account2,account1.getAddressU160().toBase58(),10,account2,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
        }
        if(false){
            ontSdk.nativevm().ong().sendApprove(account2,account1.getAddressU160().toBase58(),10,account2,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
        }
        if(false){
            ontSdk.nativevm().ong().sendTransferFrom(account1,account2.getAddressU160().toBase58(),account1.getAddressU160().toBase58(),10,account2,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
        }
        System.out.println("account1 unclaimOng:" + ontSdk.nativevm().ong().unclaimOng(account1.getAddressU160().toBase58()));
        if(true){
            ontSdk.nativevm().ong().claimOng(account1,account1.getAddressU160().toBase58(),10,account2,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
        }
        System.out.println("account1 unclaimOng:" + ontSdk.nativevm().ong().unclaimOng(account1.getAddressU160().toBase58()));
        System.out.println("account1:" + ontSdk.getConnect().getBalance(account1.getAddressU160().toBase58()));
        System.out.println("account2:" + ontSdk.getConnect().getBalance(account2.getAddressU160().toBase58()));
    }

    @Test
    public void accuntTest() throws Exception {

        String prikey = "75de8489fcb2dcaf2ef3cd607feffde18789de7da129b5e97c81e001793cb7cf";
        com.github.ontio.account.Account account2 = new com.github.ontio.account.Account(Helper.hexToBytes(prikey),SignatureScheme.SHA256WITHECDSA);
        Account account = ontSdk.getWalletMgr().importAccount("C37723zCK+H7oFQEeyYK+DDWD3o7H5oIAK5uMnKsHtpZ2365b8zen4fGapJLjaOr","11111111","AUDDJAsG1nQo115gXh8rtfocgfT2QLqLDp",Base64.decode("qWmSyQtulLImm5WrmqEBzw==",Base64.NO_WRAP));
        int aa = 0;

        com.github.ontio.account.Account account1 = ontSdk.getWalletMgr().getAccount("AUDDJAsG1nQo115gXh8rtfocgfT2QLqLDp","11111111",Base64.decode("qWmSyQtulLImm5WrmqEBzw==",Base64.NO_WRAP));
        System.out.println("account1:" + ontSdk.getConnect().getBalance(account1.getAddressU160().toBase58()));
        System.out.println("account2:" + ontSdk.getConnect().getBalance(account2.getAddressU160().toBase58()));
        if(false){
            ontSdk.nativevm().ont().sendTransfer(account2,account1.getAddressU160().toBase58(),10,account2,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
        }
        if(false){
            ontSdk.nativevm().ont().sendApprove(account2,account1.getAddressU160().toBase58(),10,account2,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
        }
        if(true){
            ontSdk.nativevm().ont().sendTransferFrom(account1,account2.getAddressU160().toBase58(),account1.getAddressU160().toBase58(),10,account2,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
        }
        System.out.println("account1:" + ontSdk.getConnect().getBalance(account1.getAddressU160().toBase58()));
        System.out.println("account2:" + ontSdk.getConnect().getBalance(account2.getAddressU160().toBase58()));
    }

    @Test
    public void Governance() throws Exception {
        String password = "111111";
        String privatekey1 = "54ca4db481966046b15f8d15ff433e611c49ab8e68a279ebf579e4cfd108196d";
        com.github.ontio.account.Account payerAcct = new com.github.ontio.account.Account(Helper.hexToBytes(privatekey1),SignatureScheme.SHA256WITHECDSA);

        String privatekey9 = "1383ed1fe570b6673351f1a30a66b21204918ef8f673e864769fa2a653401114";
        String privatekey8 = "87a209d232d6b4f3edfcf5c34434aa56871c2cb204c263f6b891b95bc5837cac";
        String privatekey7 = "24ab4d1d345be1f385c75caf2e1d22bdb58ef4b650c0308d9d69d21242ba8618";

        com.github.ontio.account.Account account9 = new com.github.ontio.account.Account(Helper.hexToBytes(privatekey9),SignatureScheme.SHA256WITHECDSA);

        String prikey = "75de8489fcb2dcaf2ef3cd607feffde18789de7da129b5e97c81e001793cb7cf";
//            prikey = "523c5fcf74823831756f0bcb3634234f10b3beb1c05595058534577752ad2d9f";
        String adminPrivateKey = "957419a5ceaf5bd40e83e0fc59e71b0d7fef68149e3ea99f79149afc441549cd";
        String adminPrivateKey2 = "ca53fa4f53ed175e39da86f4e02cd87638652cdbdcdae594c81d2e2f2f673745";
        com.github.ontio.account.Account account = new com.github.ontio.account.Account(Helper.hexToBytes(prikey),SignatureScheme.SHA256WITHECDSA);
        com.github.ontio.account.Account account8 = new com.github.ontio.account.Account(Helper.hexToBytes(privatekey8),SignatureScheme.SHA256WITHECDSA);
        com.github.ontio.account.Account account7 = new com.github.ontio.account.Account(Helper.hexToBytes(privatekey7),SignatureScheme.SHA256WITHECDSA);
        com.github.ontio.account.Account adminAccount = new com.github.ontio.account.Account(Helper.hexToBytes(adminPrivateKey),SignatureScheme.SHA256WITHECDSA);
        com.github.ontio.account.Account adminAccount2 = new com.github.ontio.account.Account(Helper.hexToBytes(adminPrivateKey2),SignatureScheme.SHA256WITHECDSA);

        if(false){
            ontSdk.nativevm().ont().sendTransfer(account,account9.getAddressU160().toBase58(),100,payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
            System.out.println("account: " + ontSdk.getConnect().getBalance(account.getAddressU160().toBase58()));
            long aa = ontSdk.nativevm().ong().unclaimOng(account.getAddressU160().toBase58());
            System.out.println("account: " + aa);
            ontSdk.nativevm().ong().claimOng(account,account.getAddressU160().toBase58(),aa,payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
        }

        if(false){
//            Identity identity = ontSdk.getWalletMgr().createIdentityFromPriKey(password,adminPrivateKey);
//            String txhash = ontSdk.nativevm().ontId().sendRegister(identity,password,payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
            Identity identity8 = ontSdk.getWalletMgr().getWallet().getIdentity(Common.didont + account8.getAddressU160().toBase58());
            String txhash2 = ontSdk.nativevm().ontId().sendRegister(identity8,password,payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);

            Identity identity = ontSdk.getWalletMgr().getWallet().getIdentity(Common.didont + account.getAddressU160().toBase58());
            String txhash = ontSdk.nativevm().ontId().sendRegister(identity,password,payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
//            Object obj = ontSdk.getConnect().getSmartCodeEvent(txhash);
//            System.out.println(obj);
            System.out.println(ontSdk.getConnect().getSmartCodeEvent(txhash2));
        }

        Identity identity = ontSdk.getWalletMgr().getWallet().getIdentity(Common.didont + account8.getAddressU160().toBase58());
        System.out.println("identity:" + ontSdk.nativevm().ontId().sendGetDDO(identity.ontid));
        System.out.println("account:" + ontSdk.getConnect().getBalance(account.getAddressU160().toBase58()));
        System.out.println("account:" + account.getAddressU160().toBase58());

        Identity adminOntid = ontSdk.getWalletMgr().getWallet().getIdentity(Common.didont + adminAccount.getAddressU160().toBase58());
        adminOntid = ontSdk.getWalletMgr().getWallet().getIdentity(Common.didont + account.getAddressU160().toBase58());
//        com.github.ontio.account.Account adminAccount = new com.github.ontio.account.Account(Helper.hexToBytes(adminPrivateKey),SignatureScheme.SHA256WITHECDSA);
//        com.github.ontio.account.Account adminAccount2 = new com.github.ontio.account.Account(Helper.hexToBytes(adminPrivateKey2),SignatureScheme.SHA256WITHECDSA);

        if(false){
            String contractAddr = "0000000000000000000000000000000000000007";
//                Identity adminOntid = sdk.getWalletMgr().getWallet().getIdentity("did:ont:AazEvfQPcQ2GEFFPLF1ZLwQ7K5jDn81hve");
//                String txhash = ontSdk.nativevm().auth().assignFuncsToRole(adminOntid.ontid,password,adminOntid.controls.get(0).getSalt(),1,contractAddr,"role",new String[]{"registerCandidate"},payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
//                String txhash = ontSdk.nativevm().auth().assignOntIdsToRole(adminOntid.ontid,password,adminOntid.controls.get(0).getSalt(),1,contractAddr,"role",new String[]{identity.ontid},payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
//                Thread.sleep(6000);
//                Object obj = ontSdk.getConnect().getSmartCodeEvent(txhash);
//                System.out.println(obj);
            System.out.println("");
            Object obj2 = ontSdk.nativevm().auth().verifyToken(identity.ontid,password,identity.controls.get(0).getSalt(),1,contractAddr,"registerCandidate");
            System.out.println(obj2);

        }
        if(false){
            String txhash = ontSdk.nativevm().governance().registerCandidate(account,Helper.toHexString(account7.serializePublicKey()),100000,identity.ontid,password,identity.controls.get(0).getSalt(),1,account,ontSdk.DEFAULT_GAS_LIMIT,0);
//                String txhash = ontSdk.nativevm().governance().unRegisterCandidate(account,Helper.toHexString(account7.serializePublicKey()),payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
            Object obj = ontSdk.getConnect().getSmartCodeEvent(txhash);
            System.out.println(obj);

        }
        if(false){
//                Identity adminOntid = sdk.getWalletMgr().getWallet().getIdentity("did:ont:AazEvfQPcQ2GEFFPLF1ZLwQ7K5jDn81hve");
//            String txhash = ontSdk.nativevm().governance().rejectCandidate(account,Helper.toHexString(account7.serializePublicKey()),payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
//                String txhash = ontSdk.nativevm().governance().approveCandidate(account,Helper.toHexString(account7.serializePublicKey()),payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
//                String txhash = ontSdk.nativevm().governance().voteForPeer(account,new String[]{Helper.toHexString(account7.serializePublicKey())},new long[]{100},payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
//String txhash = ontSdk.nativevm().governance().unVoteForPeer(account,new String[]{Helper.toHexString(account7.serializePublicKey())},new long[]{100},payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
//                  String txhash = ontSdk.nativevm().governance().quitNode(account,Helper.toHexString(account7.serializePublicKey()),payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
            String txhash = ontSdk.nativevm().governance().withdraw(account,new String[]{Helper.toHexString(account7.serializePublicKey())},new long[]{100000},payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
//                String txhash = ontSdk.nativevm().governance().commitDpos(account,payerAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);

            System.out.println(ontSdk.getConnect().getSmartCodeEvent(txhash));
//                System.out.println("account9" +sdk.getConnect().getBalance( account9.getAddressU160().toBase58()));
        }
        if(true) {
            System.out.println("account:" + ontSdk.getConnect().getBalance(account.getAddressU160().toBase58()));
            String peerInfo = ontSdk.nativevm().governance().getPeerInfo(Helper.toHexString(account7.serializePublicKey()));
            System.out.println("peerInfo:" + peerInfo);
            VoteInfo voteInfo = ontSdk.nativevm().governance().getVoteInfo(Helper.toHexString(account7.serializePublicKey()),account.getAddressU160());
            if(voteInfo != null){
                System.out.println("voteInfo:" + voteInfo.json());
            }

        }
    }

    @Test
    public void ontidtest2() throws Exception {
        com.github.ontio.account.Account payAcct = new com.github.ontio.account.Account(Helper.hexToBytes(privatekey1),SignatureScheme.SHA256WITHECDSA);
        Identity identity = ontSdk.getWalletMgr().createIdentity(password);
        Identity identity1 = ontSdk.getWalletMgr().createIdentity(password);

//
        Attribute[] attributes = new Attribute[1];
        attributes[0] = new Attribute("key1".getBytes(),"String".getBytes(),"value1".getBytes());
//        ontSdk.nativevm().ontId().sendRegisterWithAttrs(identity,password,identity.controls.get(0).getSalt(),attributes,payAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
//        ontSdk.nativevm().ontId().sendRegister(identity,password,identity.controls.get(0).getSalt(),payAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
//        ontSdk.nativevm().ontId().sendRegister(identity,password,identity.controls.get(0).getSalt(),payAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
        Thread.sleep(6000);
        System.out.println(ontSdk.nativevm().ontId().sendGetDDO(identity.ontid));
//        ontSdk.nativevm().ontId().sendAddAttributes(identity.ontid,password,identity.controls.get(0).getSalt(),attributes,payAcct,ontSdk.DEFAULT_GAS_LIMIT,0);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Issuer", identity.ontid);
        map.put("Subject", identity1.ontid);

        Map clmRevMap = new HashMap();
        clmRevMap.put("typ","AttestContract");
        clmRevMap.put("addr",identity1.ontid.replace(Common.didont,""));

        String claim = ontSdk.nativevm().ontId().createOntIdClaim(identity.ontid,password,identity1.controls.get(0).getSalt(), "claim:context", map, map,clmRevMap,System.currentTimeMillis()/1000 +100000);
        Thread.sleep(6000);
        System.out.println(ontSdk.nativevm().ontId().sendGetDDO(identity.ontid));
    }

    @Test
    public void ontidtest() throws Exception {
        com.github.ontio.account.Account payAcct = new com.github.ontio.account.Account(Helper.hexToBytes(privatekey1),SignatureScheme.SHA256WITHECDSA);
        Identity identity = ontSdk.getWalletMgr().createIdentity(password);

//        ontSdk.nativevm().ontId().sendRegister(identity,password,identity.controls.get(0).getSalt(),payAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
        Thread.sleep(6000);
        System.out.println(ontSdk.nativevm().ontId().sendGetDDO(identity.ontid));

        System.exit(0);

        ontSdk.nativevm().ontId().sendAddPubKey(identity.ontid,password,identity.controls.get(0).getSalt(),Helper.toHexString(payAcct.serializePublicKey()),payAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
        System.out.println(Helper.toHexString(payAcct.serializePublicKey()));
//        Attribute[] attributes = new Attribute[1];
//        attributes[0] = new Attribute("key1".getBytes(),"String".getBytes(),"value1".getBytes());
//        ontSdk.nativevm().ontId().sendAddAttributes(identity.ontid,password,identity.controls.get(0).getSalt(),attributes,payAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
////        ontSdk.nativevm().ontId().sendRegisterWithAttrs(identity,password,identity.controls.get(0).getSalt(),attributes,payAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
        Thread.sleep(6000);
        ontSdk.nativevm().ontId().sendRemovePubKey(identity.ontid,password,identity.controls.get(0).getSalt(),Helper.toHexString(payAcct.serializePublicKey()),payAcct,ontSdk.DEFAULT_GAS_LIMIT,0);
        Thread.sleep(6000);
        System.out.println("resultresultresultresult:");
        System.out.println(ontSdk.nativevm().ontId().sendGetDDO(identity.ontid));
    }

    @Test
    public void addresstest() throws Exception {
        Identity identity = ontSdk.getWalletMgr().createIdentity(password);
        ontSdk.getWalletMgr().importIdentity(identity.controls.get(0).key,password,identity.controls.get(0).getSalt(),identity.ontid.replace(Common.didont,""));
//                Account account = ontSdk.getWalletMgr().createAccount(password);
//                ontSdk.getWalletMgr().importAccountWithRandomLabel(identity.controls.get(0).key,password,identity.ontid.replace(Common.didont,""),identity.controls.get(0).getSalt());
//        System.out.println(account.address);
//        int aa = 0;
    }

    @Test
    public void accountDemo() throws Exception {


//        String privateKey = "0bc8c1f75a028672cd42c221bf81709dfc7abbbaf0d87cb6fdeaf9a20492c194";
        com.github.ontio.account.Account account1 = new com.github.ontio.account.Account(Helper.hexToBytes(privatekey1), SignatureScheme.SHA256WITHECDSA);
        byte[] salt = new byte[]{(byte)251,(byte)155,(byte)65,(byte)228,(byte)3,(byte)251,(byte)77,(byte)136,(byte)106,(byte)44,(byte)2,(byte)255,(byte)194,(byte)185,(byte)234,(byte)196};
        String aa = account1.exportGcmEncryptedPrikey("111111",salt,4096);
        System.out.println(aa);
    }

    @Test
    public void transferTest() throws Exception {
        String privateKey = "0bc8c1f75a028672cd42c221bf81709dfc7abbbaf0d87cb6fdeaf9a20492c194";
        com.github.ontio.account.Account acct1 = new com.github.ontio.account.Account(Helper.hexToBytes(privateKey), ontSdk.defaultSignScheme);
        com.github.ontio.account.Account acct2 = new com.github.ontio.account.Account(Helper.hexToBytes(privatekey1), ontSdk.defaultSignScheme);
        com.github.ontio.account.Account account1 = new com.github.ontio.account.Account(Helper.hexToBytes("2b5887abb1421ab101714906c8578aac340d2713f3b7b34135fed191686f9087"), SignatureScheme.SHA256WITHECDSA);


        System.out.println("account1：" + ontSdk.getConnect().getBalance(account1.getAddressU160().toBase58()));
//        ontSdk.nativevm().ong().claimOng(acct2,acct2.getAddressU160().toBase58(),1000,acct2,ontSdk.DEFAULT_GAS_LIMIT,0);
        String txhash = "";
        if(true){
            txhash = ontSdk.nativevm().ont().sendTransfer(account1,acct2.getAddressU160().toBase58(),10,account1,ontSdk.DEFAULT_GAS_LIMIT,0);
//            txhash = ontSdk.nativevm().ont().sendApprove(account1,acct1.getAddressU160().toBase58(),10,account1,ontSdk.DEFAULT_GAS_LIMIT,0);
//            txhash = ontSdk.nativevm().ont().sendTransferFrom(acct1,account1.getAddressU160().toBase58(),acct1.getAddressU160().toBase58(),1000,account1,ontSdk.DEFAULT_GAS_LIMIT,0);
//            ontSdk.nativevm().ong().claimOng(account1,account1.getAddressU160().toBase58(),397022742650L,account1,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
            System.out.print(ontSdk.getConnect().getSmartCodeEvent(txhash));

        }
        System.out.println(ontSdk.getConnect().getBalance(account1.getAddressU160().toBase58()));
        System.out.println(account1.getAddressU160().toBase58());
        System.out.println("");
//        System.out.println(ontSdk.nativevm().ong().queryBalanceOf(account1.getAddressU160().toBase58()));
//        System.out.println("********" + ontSdk.nativevm().ong().queryAllowance(account1.getAddressU160().toBase58(),acct1.getAddressU160().toBase58()));
        if(false){
            System.out.println(ontSdk.nativevm().ong().queryBalanceOf(account1.getAddressU160().toBase58()));
            System.out.println(ontSdk.nativevm().ong().unclaimOng(account1.getAddressU160().toBase58()));
            ontSdk.nativevm().ong().claimOng(account1,account1.getAddressU160().toBase58(),21071968349040L,account1,ontSdk.DEFAULT_GAS_LIMIT,0);
            Thread.sleep(6000);
            System.out.println(ontSdk.nativevm().ong().queryBalanceOf(account1.getAddressU160().toBase58()));
        }

        if(false){
            System.out.print(ontSdk.getConnect().getSmartCodeEvent(Helper.reverse(txhash)));
            long balance2 = ontSdk.nativevm().ont().queryBalanceOf(account1.getAddressU160().toBase58());
            System.out.println(balance2);

            long balance3 = ontSdk.nativevm().ont().queryBalanceOf(acct1.getAddressU160().toBase58());
            System.out.println(balance3);
        }

    }

    @Test
    public void removeAccount() throws Exception {
        List<Account> accounts = wallet.getAccounts();
        int origSize = accounts.size();
        Account account = walletMgr.createAccount("123456");
        assertEquals(accounts.size(),origSize+1);

        wallet.removeAccount(account.address);
        assertEquals(accounts.size(),origSize);

    }

    @Test
    public void removeAccountError(){
        boolean isSuccess =wallet.removeIdentity("");
        assertFalse(isSuccess);
    }

    @Test
    public void removeIdentity() throws Exception {
        List<Identity> identities = wallet.getIdentities();
        int origSize = identities.size();
        Identity identity = walletMgr.createIdentity("123456");
        assertEquals(identities.size(),origSize+1);

        wallet.removeIdentity(identity.ontid);
        assertEquals(identities.size(),origSize);
    }

    @Test
    public void removeIdentityError(){
        boolean isSuccess = wallet.removeIdentity("");
        assertFalse(isSuccess);
    }
}