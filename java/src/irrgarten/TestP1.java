
package irrgarten;

/**
 *
 * @author Manuel Cantero
 */
public class TestP1 {
    
    public static void main(String[] args) {
/*
        Como hacer algo en la interfaz:

CREAMOS UN JDIALOG QUE ES UN BUTON CON SI Y NO Y UN TEXTO

- Creamos un atributo de instancia:    
private boolean swap = false;

- Creamos un getter:
public boolean getIndication(){
        setVisible(true);
        return swap;
    }
    
    Hacemos el boton con si y no:

  private void NoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoButtonActionPerformed
        swap = false;
        this.dispose();
    }//GEN-LAST:event_NoButtonActionPerformed

    private void YesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YesButtonActionPerformed
        swap = true;
        this.dispose();
    }
    
---------------------------------------------------------------------

EN UI: 
    Creamos en UI el metodo: (Tenemos que añadirlo a la grafica y a la de texto)
    public boolean swapWeapons();
    
---------------------------------------------------------------------
   
   
En TEXT_UI inicializamos el otro metodo:
@Override
public boolean swapWeapons(){
        return false;
    }
---------------------------------------------------------------------

En graphicUI creamos un atributo privado:
    private SwappingButton swapButton;
    
    Inicializamos en el contructor de graphicUI:       
    swapButton = new SwappingButton(this,true);
    
    Hacemos override del metodo creado en UI:
	@Override
    public boolean swapWeapons(){
        return swapButton.getIndication();
    }

---------------------------------------------------------------------

En Controller:

public void play() {
        boolean endOfGame = false;
        while (!endOfGame) {
            view.showGame(game.getGameState());
            //Descomentar para usar el boton de intercambio de armas
       
/**

/*
            if(!game.getGameState().getSwap()){
                if(view.swapWeapons()){
                    game.swapEvent();
                    view.showGame(game.getGameState());
                }
            }
            * */
 /*          
            Directions direction = view.nextMove();
            endOfGame = game.nextStep(direction);
        }
      view.showGame(game.getGameState());        
    }
    
---------------------------------------------------------------------
    
En GAME:

- Creamos un atributo privado 

   	private boolean swap = false;
   
- CAMBIAMOS getGameState ya que llamamos al constructor de este (linea 67)
    
	    GameState gamestate = new GameState(labyrinth.toString(),
		        players_s,monsters_s,currentPlayerIndex, finished(),log, swap);
		
		return gamestate;
	    }
- Creamos un metodo public que controla lo que hacemos:

    public void swapEvent(){
        if(!swap){
            currentPlayer.swapWeapons(players.get((currentPlayerIndex+1)%players.size()));
            swap = true;
        }
    }


---------------------------------------------------------------------


En GAMESTATE

- creamos un atributo privado, 

    private boolean swap = false;
    
- le hacemos un getter 

    public boolean getSwap(){
        return swap;
    }

- lo añadimos al consttructor:

public GameState(String labyrinth, String players, String monsters, int currentPlayer, boolean winner, String log, boolean swap) {
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
        this.swap = swap;
    }

---------------------------------------------------------------------


Si hay que cambiar en fuzzyPlayer llamamos a super y hacemos lo que nos pidan:
 @Override
    public void swapWeapons(Player other){
        super.swapWeapons(other);
        receiveReward();
    }
    
 ---------------------------------------------------------------------   
    
    En Player:

 public void swapWeapons(Player other){
        ArrayList<CombatElement> tmp = weapons;
        weapons = other.weapons;
        other.weapons = tmp;
    }
    */
 
    }
}
