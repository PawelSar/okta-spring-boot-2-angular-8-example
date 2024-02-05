package com.example.demo;

import net.pushover.client.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shopping")
@CrossOrigin(origins = "*")
public class EmailControllerPush {


    @Resource(name = "shoppingListService")
    private ShoppingListService shoppingListService;

    @GetMapping("/sendPush")
    public String sendZakupy() throws PushoverException {

String shoppingList = shoppingListService.getAllEvents().get(0).getShoppingList();

        String shoppingListWithNs = shoppingList;

        shoppingListWithNs = shoppingListWithNs.replace(",", "\n");


    PushoverClient client = new PushoverRestClient();

    Status result = client.pushMessage(PushoverMessage.builderWithApiToken("au24m39a8e262t3n3uw7gs6o5xp8o4")
            .setUserId("g4y4x1iupu8eyqnhe9jx8eied2uynu")
            .setMessage(shoppingListWithNs)
            .setPriority(MessagePriority.NORMAL) // HIGH|NORMAL|QUIET
            .setTitle("Lista zakupow")
            //.setUrl("http://31.179.37.99:4200/")
            //.setTitleForURL("Kalendarz online")
            .setSound("magic")
            .build());

        System.out.println(String.format("status: %d, request id: %s", result.getStatus(), result.getRequestId()));


        return "200";
    }

    @PostMapping("/saveShoppingList")
    public ShoppingListData saveShoppingList(final @RequestBody ShoppingListData eventData) throws PushoverException {

        List<String> elektronika = new ArrayList<String>();
        List<String> samochodowe = new ArrayList<String>();
        List<String> pieczywo = new ArrayList<String>();
        List<String> zabawki = new ArrayList<String>();
        List<String> papiernicze = new ArrayList<String>();
        List<String> niemowlece = new ArrayList<String>();
        List<String> kosmetyki = new ArrayList<String>();
        List<String> kawy = new ArrayList<String>();
        List<String> slodycze = new ArrayList<String>();
        List<String> warzywa = new ArrayList<String>();
        List<String> makarony = new ArrayList<String>();
        List<String> przyprawy = new ArrayList<String>();
        List<String> sosy = new ArrayList<String>();
        List<String> mieso = new ArrayList<String>();
        List<String> mrozonki = new ArrayList<String>();
        List<String> alkohol = new ArrayList<String>();
        List<String> soki = new ArrayList<String>();
        List<String> nabial = new ArrayList<String>();
        List<String> woda = new ArrayList<String>();
        List<String> inne = new ArrayList<String>();


        List<String> shoppingList = new ArrayList<>(Arrays.asList(eventData.getShoppingList().split(",")));

        for(String shoppingArticle : shoppingList){

            if(shoppingArticle.toLowerCase().contains("bater") || shoppingArticle.toLowerCase().contains("spryski") || shoppingArticle.toLowerCase().contains("elektro") || shoppingArticle.toLowerCase().contains("worki do odku")){
                elektronika.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("bulk") || shoppingArticle.toLowerCase().contains("bułk") || shoppingArticle.toLowerCase().contains("chleb") || shoppingArticle.toLowerCase().contains("bagiet") || shoppingArticle.toLowerCase().contains("czosndo") || shoppingArticle.toLowerCase().contains("tarta") || shoppingArticle.toLowerCase().contains("bulek") || shoppingArticle.toLowerCase().contains("bułek")) {
                pieczywo.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("zabaw") || shoppingArticle.toLowerCase().contains("lal") || shoppingArticle.toLowerCase().contains("samoch") || shoppingArticle.toLowerCase().contains("prezent")) {
                zabawki.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("ksiaz") || shoppingArticle.toLowerCase().contains("papier") || shoppingArticle.toLowerCase().contains("gazet") || shoppingArticle.toLowerCase().contains("kolorow")) {
                papiernicze.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("pieluch") || shoppingArticle.toLowerCase().contains("pamper") || shoppingArticle.toLowerCase().contains("socz") || shoppingArticle.toLowerCase().contains("plukania") || shoppingArticle.toLowerCase().contains("prosz") || shoppingArticle.toLowerCase().contains("mleko dla oli") || shoppingArticle.toLowerCase().contains("mleko ola")) {
                niemowlece.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("zel") || shoppingArticle.toLowerCase().contains("żel") || shoppingArticle.toLowerCase().contains("past") || shoppingArticle.toLowerCase().contains("szampon") || shoppingArticle.toLowerCase().contains("antypersp") || shoppingArticle.toLowerCase().contains("kulki") || shoppingArticle.toLowerCase().contains("oczyszcz") || shoppingArticle.toLowerCase().contains("kapiel")) {
                kosmetyki.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("kaw") || shoppingArticle.toLowerCase().contains("herbat") || shoppingArticle.toLowerCase().contains("gumy") || shoppingArticle.toLowerCase().contains("maka") || shoppingArticle.toLowerCase().contains("mąka")) {
                kawy.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("slod") || shoppingArticle.toLowerCase().contains("słod") || shoppingArticle.toLowerCase().contains("czekol") || shoppingArticle.toLowerCase().contains("cukier") || shoppingArticle.toLowerCase().contains("orzech") || shoppingArticle.toLowerCase().contains("wafel") || shoppingArticle.toLowerCase().contains("zelk") || shoppingArticle.toLowerCase().contains("żelk")) {
                slodycze.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("ziemniaki") || shoppingArticle.toLowerCase().contains("cebula") || shoppingArticle.toLowerCase().contains("czosnek") || shoppingArticle.toLowerCase().contains("marchew") || shoppingArticle.toLowerCase().contains("papryk") || shoppingArticle.toLowerCase().contains("kapust") || shoppingArticle.toLowerCase().contains("pieczar") || shoppingArticle.toLowerCase().contains("sala") || shoppingArticle.toLowerCase().contains("sała") || shoppingArticle.toLowerCase().contains("jabł") || shoppingArticle.toLowerCase().contains("jabl") || shoppingArticle.toLowerCase().contains("winogr") || shoppingArticle.toLowerCase().contains("grusz") || shoppingArticle.toLowerCase().contains("pomara") || shoppingArticle.toLowerCase().contains("mandar") || shoppingArticle.toLowerCase().contains("dyni") || shoppingArticle.toLowerCase().contains("karma") || shoppingArticle.toLowerCase().contains("saszet")) {
                warzywa.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("makaron") || shoppingArticle.toLowerCase().contains("spaghett") || shoppingArticle.toLowerCase().contains("krajanka") || shoppingArticle.toLowerCase().contains("do roso") || shoppingArticle.toLowerCase().contains("fussili") || shoppingArticle.toLowerCase().contains("swider") || shoppingArticle.toLowerCase().contains("świder") || shoppingArticle.toLowerCase().contains("muszel")) {
                makarony.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("sos") || shoppingArticle.toLowerCase().contains("kukurydz") || shoppingArticle.toLowerCase().contains("fasol") || shoppingArticle.toLowerCase().contains("oliwk") || shoppingArticle.toLowerCase().contains("olej") || shoppingArticle.toLowerCase().contains("oliwa") || shoppingArticle.toLowerCase().contains("sery") || shoppingArticle.toLowerCase().contains("sos do spag")) {
                sosy.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("mieso") || shoppingArticle.toLowerCase().contains("filet") || shoppingArticle.toLowerCase().contains("mielon") || shoppingArticle.toLowerCase().contains("wolowin") || shoppingArticle.toLowerCase().contains("wołowin") || shoppingArticle.toLowerCase().contains("zestaw do roso") || shoppingArticle.toLowerCase().contains("udk") || shoppingArticle.toLowerCase().contains("skrzyde") || shoppingArticle.toLowerCase().contains("kiełba") || shoppingArticle.toLowerCase().contains("kielba") || shoppingArticle.toLowerCase().contains("szynk") || shoppingArticle.toLowerCase().contains("parów") || shoppingArticle.toLowerCase().contains("parow") || shoppingArticle.toLowerCase().contains("ser")) {
                mieso.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("mrozon") || shoppingArticle.toLowerCase().contains("pyzy") || shoppingArticle.toLowerCase().contains("pierog") || shoppingArticle.toLowerCase().contains("warzywa na patel") || shoppingArticle.toLowerCase().contains("lody") || shoppingArticle.toLowerCase().contains("pizz")) {
                mrozonki.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("alko") || shoppingArticle.toLowerCase().contains("win") || shoppingArticle.toLowerCase().contains("piw") || shoppingArticle.toLowerCase().contains("cydr") || shoppingArticle.toLowerCase().contains("martini")) {
                alkohol.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("sok")) {
                soki.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("mleko") || shoppingArticle.toLowerCase().contains("jogurt") || shoppingArticle.toLowerCase().contains("serek") || shoppingArticle.toLowerCase().contains("śmietan") || shoppingArticle.toLowerCase().contains("smietana") || shoppingArticle.toLowerCase().contains("fantazja") || shoppingArticle.toLowerCase().contains("wiejsk") || shoppingArticle.toLowerCase().contains("jaj") || shoppingArticle.toLowerCase().contains("droż") || shoppingArticle.toLowerCase().contains("droz")) {
                nabial.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("woda")) {
                woda.add(shoppingArticle);
            } else if(shoppingArticle.toLowerCase().contains("przypr") || shoppingArticle.toLowerCase().contains("sol") || shoppingArticle.toLowerCase().contains("sól") || shoppingArticle.toLowerCase().contains("pieprz")) {
                przyprawy.add(shoppingArticle);
            } else {
                inne.add(shoppingArticle);
            }

        }

        List<String> newList = new ArrayList<>();
        newList.addAll(elektronika);
        newList.addAll(samochodowe);
        newList.addAll(pieczywo);
        newList.addAll(zabawki);
        newList.addAll(papiernicze);
        newList.addAll(niemowlece);
        newList.addAll(kosmetyki);
        newList.addAll(kawy);
        newList.addAll(slodycze);
        newList.addAll(warzywa);
        newList.addAll(makarony);
        newList.addAll(przyprawy);
        newList.addAll(sosy);
        newList.addAll(mieso);
        newList.addAll(mrozonki);
        newList.addAll(alkohol);
        newList.addAll(soki);
        newList.addAll(nabial);
        newList.addAll(woda);
        newList.addAll(inne);

        String listString = newList.stream().map(Object::toString)
                .collect(Collectors.joining(","));

Long id = 0L;

        if(shoppingListService.getAllEvents().size() > 0 && shoppingListService.getAllEvents().get(0) != null){
            id = shoppingListService.getAllEvents().get(0).getId();
        } else {
           id = 123L; 
        }
        

        ShoppingListData updatedEvent = shoppingListService.getOne(id);

        updatedEvent.setShoppingList(listString);
        updatedEvent.setId(id);

        return shoppingListService.saveEvent(updatedEvent);

    }

    @GetMapping("/loadShoppingList")
    public ShoppingListData loadZakupy() {
ShoppingListData shoppingList = new ShoppingListData();

if(shoppingListService.getAllEvents().size() > 0 && shoppingListService.getAllEvents().get(0) != null){
    shoppingList = shoppingListService.getAllEvents().get(0);
    } 
        

        return shoppingList;
    }

}
