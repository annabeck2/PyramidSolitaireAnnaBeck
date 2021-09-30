import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for Pyramid Solitaire Textual Controller Class.
 */
public class PyramidSolitaireTextualControllerTest {

  PyramidSolitaireModel<Card> basicPyramidSolitaireModel = new BasicPyramidSolitaire();
  PyramidSolitaireModel<Card> relaxedPyramidSolitaireModel = new RelaxedPyramidSolitaire();
  PyramidSolitaireModel<Card> multiPyramidSolitaireModel = new MultiPyramidSolitaire();

  @Test (expected = IllegalStateException.class)
  public void testRelaxed() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(relaxedPyramidSolitaireModel,
        relaxedPyramidSolitaireModel.getDeck(),false, -1, 0);
  }

  @Test (expected = IllegalStateException.class)
  public void testMulti() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(multiPyramidSolitaireModel,
        multiPyramidSolitaireModel.getDeck(),true, 10, 1);

  }



  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameNullModel() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(null,
        basicPyramidSolitaireModel.getDeck(),false, 7, 6);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameNullModelR() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(null,
        relaxedPyramidSolitaireModel.getDeck(),false, 7, 6);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameNullModelM() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(null,
        multiPyramidSolitaireModel.getDeck(),false, 7, 6);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testPlayGameNullDeck() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        null,false, 7, 6);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testPlayGameNullDeckR() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(relaxedPyramidSolitaireModel,
        null,false, 7, 6);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testPlayGameNullDeckM() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(multiPyramidSolitaireModel,
        null,false, 7, 6);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGameNullReadable() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    PyramidSolitaireTextualController badModelTextCont =
        new PyramidSolitaireTextualController(null, out);
    badModelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 7, 6);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGameNullAppendable() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    PyramidSolitaireTextualController badModelTextCont =
        new PyramidSolitaireTextualController(in, null);
    badModelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 7, 6);

  }

  @Test(expected = IllegalStateException.class)
  public void testGameCannotStartGameNoRows() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    PyramidSolitaireTextualController badModelTextCont =
        new PyramidSolitaireTextualController(in, out);
    badModelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 0, 7);
  }

  @Test(expected = IllegalStateException.class)
  public void testGameCannotStartGameNoDrawCards() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    PyramidSolitaireTextualController badModelTextCont =
        new PyramidSolitaireTextualController(in, out);
    badModelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, -1);
  }

  @Test
  public void testPlayGameRm1() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm1 5 3");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 5, 2);
    assertEquals(null, basicPyramidSolitaireModel.getCardAt(4,2));
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString()
            + "\nScore: " + basicPyramidSolitaireModel.getScore() + "\n");
  }

  @Test
  public void testPlayGameRm1R() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm1 5 3");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(relaxedPyramidSolitaireModel,
        relaxedPyramidSolitaireModel.getDeck(),false, 4, 2);
    assertEquals(null, relaxedPyramidSolitaireModel.getCardAt(4,2));
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(relaxedPyramidSolitaireModel).toString()
            + "\nScore: " + relaxedPyramidSolitaireModel.getScore() + "\n");
  }

  @Test
  public void testPlayGameRm1M() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm1 5 2");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(multiPyramidSolitaireModel,
        multiPyramidSolitaireModel.getDeck(),false, 5, 2);
    assertEquals(null, multiPyramidSolitaireModel.getCardAt(4,1));
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(multiPyramidSolitaireModel).toString()
            + "\nScore: " + multiPyramidSolitaireModel.getScore() + "\n");
  }

  @Test
  public void testPlayGameRm2() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm2 5 2 5 4");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 5, 5);
    assertEquals(null, basicPyramidSolitaireModel.getCardAt(4,1));
    assertEquals(null, basicPyramidSolitaireModel.getCardAt(4,3));
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString()
            + "\nScore: " + basicPyramidSolitaireModel.getScore() + "\n");
  }

  @Test
  public void testPlayGameRm2R() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm2 5 2 5 4");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(relaxedPyramidSolitaireModel,
        relaxedPyramidSolitaireModel.getDeck(),false, 5, 5);
    assertEquals(null, relaxedPyramidSolitaireModel.getCardAt(4,1));
    assertEquals(null, relaxedPyramidSolitaireModel.getCardAt(4,3));
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(relaxedPyramidSolitaireModel).toString()
            + "\nScore: " + relaxedPyramidSolitaireModel.getScore() + "\n");
  }

  @Test
  public void testPlayGameRm2M() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm2 5 1 5 3");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(multiPyramidSolitaireModel,
        multiPyramidSolitaireModel.getDeck(),false, 5, 5);
    assertEquals(null, multiPyramidSolitaireModel.getCardAt(4,0));
    assertEquals(null, multiPyramidSolitaireModel.getCardAt(4,2));
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(multiPyramidSolitaireModel).toString()
            + "\nScore: " + multiPyramidSolitaireModel.getScore() + "\n");
  }

  @Test
  public void testPlayGameRmwd() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rmwd 9 5 5");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 5, 10);
    assertEquals(null, basicPyramidSolitaireModel.getCardAt(4,4));
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString()
            + "\nScore: " + basicPyramidSolitaireModel.getScore() + "\n");
  }

  @Test
  public void testPlayGameRmwdM() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rmwd 5 5 3");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(multiPyramidSolitaireModel,
        multiPyramidSolitaireModel.getDeck(),false, 5, 5);
    assertEquals(null, multiPyramidSolitaireModel.getCardAt(4,2));
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(multiPyramidSolitaireModel).toString()
            + "\nScore: " + multiPyramidSolitaireModel.getScore() + "\n");
  }

  @Test
  public void testPlayGameDDFail() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("dd 2");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 5, 1);
    assertTrue(out.toString().contains("Invalid move"));
  }


  @Test
  public void testPlayGame4Moves() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm1 5 3 rm2 5 1 5 5 rm2 5 2 5 4 rmwd 1 4 4");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 5, 5);
    assertEquals(null, basicPyramidSolitaireModel.getCardAt(3,3));
    assertTrue(out.toString().contains(new
        PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString()
            + "\nScore: " + basicPyramidSolitaireModel.getScore() + "\n"));
  }

  @Test
  public void testPlayGameRel() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rmwd 6 4 3 rm2 4 2 3 2");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(relaxedPyramidSolitaireModel,
        relaxedPyramidSolitaireModel.getDeck(),false, 4, 7);
    assertTrue(out.toString().contains(new
        PyramidSolitaireTextualView(relaxedPyramidSolitaireModel).toString()
        + "\nScore: " + relaxedPyramidSolitaireModel.getScore() + "\n"));
  }

  @Test
  public void testPlayGameRmAceWin() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rmwd 11 1 1");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 13);
    assertEquals(true, basicPyramidSolitaireModel.isGameOver());
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString());
    assertEquals("You win!", out.toString());
  }

  @Test
  public void testPlayGameQuitAfterWinCommand() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rmwd 1 1 11 q");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 13);
    assertTrue(out.toString().contains("Game quit"));
  }

  @Test
  public void testPlayGameQuitAsRow() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm1 q 2");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(relaxedPyramidSolitaireModel,
        relaxedPyramidSolitaireModel.getDeck(),false, 1, 13);
    assertTrue(out.toString().contains("Game quit"));
  }

  @Test
  public void testPlayGameQuitAsCard() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm1 3 q");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(multiPyramidSolitaireModel,
        multiPyramidSolitaireModel.getDeck(),false, 1, 13);
    assertTrue(out.toString().contains("Game quit"));
  }



  @Test
  public void testPlayGameAceLose() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 0);
    assertEquals(true, basicPyramidSolitaireModel.isGameOver());
    assertEquals("Game over. Score: 1",
        new PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString());

  }


  @Test
  public void testPlayGameAceMoreCardsInDraw() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertEquals(false, basicPyramidSolitaireModel.isGameOver());
  }

  @Test
  public void testPlayGameAceMoreCardsInDrawMulti() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(multiPyramidSolitaireModel,
        multiPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertEquals(false, multiPyramidSolitaireModel.isGameOver());
  }

  @Test
  public void testPlayGameDD() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("dd 1 dd 1 dd 1 dd 1 "
        + "dd 1 dd 1 dd 1 dd 1 dd 1 dd 1 dd 1 rmwd 1 1 1");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains(new
        PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString()
        + "\nScore: " + basicPyramidSolitaireModel.getScore() + "\n"));
  }

  @Test
  public void testPlayGameDDM() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("dd 1 dd 1 dd 1 dd 1 "
        + "dd 1 dd 1 dd 1 dd 1 dd 1 dd 1 dd 1 rmwd 1 1 1");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(multiPyramidSolitaireModel,
        multiPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains(new
        PyramidSolitaireTextualView(multiPyramidSolitaireModel).toString()
        + "\nScore: " + multiPyramidSolitaireModel.getScore() + "\n"));
  }

  // quit after a string of valid commands
  @Test
  public void testPlayGameQuit1() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in =
        new StringReader("dd 1 dd 1 dd 1 dd 1 dd 1 dd 1 dd 1 dd 1 dd 1 dd 1 dd 1 q");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Game quit"));
  }

  // quit after random letters
  @Test
  public void testPlayGameQuit2() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("asdjfnaskdfjasd q");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Game quit"));
  }

  // uppercase Quit
  @Test
  public void testPlayGameQuit3() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("Q");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Game quit"));
  }

  // lowercase quit
  @Test
  public void testPlayGameQuit4() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("q");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Game quit"));
  }

  // quit with random letters
  @Test
  public void testPlayGameQuit5() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("qasdfasdfasd");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Game quit"));
  }

  // quit before invalid command
  @Test
  public void testPlayGameQuit6() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("Qrmwd 1 1 1");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Game quit"));
  }

  // quit after a valid command
  @Test
  public void testPlayGameQuit7() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("dd 1q");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Game quit"));
  }

  @Test
  public void testPlayGameInvalidCommandDD() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("dd 2");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Invalid move"));
  }

  @Test
  public void testPlayGameInvalidCommandDD2() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("dd F");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Invalid move"));
  }

  @Test
  public void testPlayGameInvalidCommandRM1() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm1 a s");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Invalid move"));
  }

  @Test
  public void testPlayGameInvalidCommandRM2() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm2 a s 3 f");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Invalid move"));
  }

  @Test
  public void testPlayGameInvalidCommandRMWD() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rmwd 2 a s");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 1);
    assertTrue(out.toString().contains("Invalid move"));
  }

  @Test
  public void testPlayGameRMWDExtraLetters() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rmwd 11 a s 1 1");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 13);
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString());
  }

  @Test
  public void testPlayGameRM1ExtraLetters() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm1 5 a f 3");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 5, 13);
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString() + "\nScore: " +
        basicPyramidSolitaireModel.getScore() + "\n");
  }

  @Test
  public void testPlayGameRM2ExtraLetters() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rm2 5 a f f 2 afasdfasdf 5 4");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 5, 13);
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString() + "\nScore: "
            + basicPyramidSolitaireModel.getScore() + "\n");
  }


  @Test
  public void testPlayGameDDExtraLetters() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("dd extraL e t t e rs          1");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 5, 13);
    assertEquals(out.toString(),
        new PyramidSolitaireTextualView(basicPyramidSolitaireModel).toString() + "\nScore: "
            + basicPyramidSolitaireModel.getScore() + "\n");
  }

  @Test
  public void testPlayGameInvalidCommand1() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("ddd extraL e t t e rs          1");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 5, 13);
    assertTrue(out.toString().contains("Invalid move."));
  }

  @Test
  public void testPlayGameInvalidCommand2() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("extraL e t t e rs          1");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 5, 13);
    assertTrue(out.toString().contains("Invalid move."));
  }


  @Test
  public void testPlayGameInvalidCommand3() throws IOException {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("rmmwd 11 1 1");
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);
    modelTextCont.playGame(basicPyramidSolitaireModel,
        basicPyramidSolitaireModel.getDeck(),false, 1, 13);
    assertTrue(out.toString().contains("Invalid move."));
  }

}

