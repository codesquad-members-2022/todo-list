package team07.todolist.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team07.todolist.domain.Card;
import team07.todolist.dto.ResponseCard;
import team07.todolist.dto.ResponseCardWithId;
import team07.todolist.service.CardService;

@Controller
@RequestMapping("/list")
public class CardController {

  private Logger log = LoggerFactory.getLogger(CardController.class);
  private final CardService cardService;

  public CardController(CardService cardService) {
    this.cardService = cardService;
  }

  @ResponseBody
  @GetMapping
  public List<ResponseCardWithId> home() {
    List<Card> cardList = cardService.findAll();
    return cardList.stream()
        .filter(Card::isValid)
        .map(Card::createResponseCardWithId).collect(Collectors.toList());
  }

  @PostMapping
  public String save(@RequestBody ResponseCard responseCard) {
    log.debug("{}", responseCard.toString());
    cardService.save(responseCard);

    return "redirect:/list";
  }

  @PostMapping("/{row}")
  public ResponseCard post(@RequestParam int row) {

    Card card = cardService.changeRow(1, 1);

//		return new ResponseCard();
    return null;
  }

}
