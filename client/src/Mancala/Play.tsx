import React from "react";
import type { GameState } from "../gameState";
import "./Play.css";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {
  async function tryMakeMove(i) {
    try {
      const response = await fetch('mancala/api/play', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ index: i })
      });

      if (response.ok) {
        const gameState = await response.json();
        setGameState(gameState);
      } else {
          console.error(response.statusText);
      }
    } catch (error) {
      console.error(error.toString());
    }
  }

  async function tryStartGame() {
      try {
          const playerOne = gameState.players[0].name;
          const playerTwo = gameState.players[1].name;
          const response = await fetch('mancala/api/start', {
              method: 'POST',
              headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify({ nameplayer1: playerOne, nameplayer2: playerTwo })
          });

          if (response.ok) {
              const gameState = await response.json();
              setGameState(gameState);
              localStorage.setItem("MancalaGame", JSON.stringify({ inProgress: true }));
          } else {
              console.error(response.statusText);
          }
      } catch (error) {
          console.error(error.toString());
      }
  }

    function Bowl(props) {
      return (
        <button className="bowl" onClick={props.onClick}>
          {props.value}
        </button>
      );
    }

    function Kalaha(props) {
      return (
        <button className="kalaha">
          {props.value}
        </button>
      );
    }

    class Board extends React.Component {
      constructor(props) {
        super(props);
        let pitsArray = new Array(14);
        for (let i = 0; i < 7; i++) {
          pitsArray[i] = gameState.players[0].pits[i].nrOfStones;
          pitsArray[i+7] = gameState.players[1].pits[i].nrOfStones;
        }
        this.state = {
          pits: pitsArray,
        };
      }

      handleClick(i) {
        if (!(gameState.gameStatus.endOfGame || this.state.pits[i] == 0)) {
          if ((i<7 && gameState.players[0].hasTurn) || (i>=7 && gameState.players[1].hasTurn)) {
            tryMakeMove(i);
            let pitsArray = new Array(14);
            for (let i = 0; i < 7; i++) {
              pitsArray[i] = gameState.players[0].pits[i].nrOfStones;
              pitsArray[i+7] = gameState.players[1].pits[i].nrOfStones;
            }
            this.setState({
              pits: pitsArray,
            });
          }
        }
      }

      renderBowl(i) {
        return (
          <Bowl
            value={this.state.pits[i]}
            onClick={() => this.handleClick(i)}
          />
        );
      }

      renderKalaha(i) {
          return (
            <Kalaha
              value={this.state.pits[i]}
            />
          );
      }

      render() {
        let status;
        if (gameState.gameStatus.endOfGame) {
          status = 'Winner: ' + gameState.gameStatus.winner;
          localStorage.removeItem("MancalaGame");
        } else if (gameState.players[0].hasTurn) {
          status = gameState.players[0].name + '\'s turn.';
        } else {
          status = gameState.players[1].name + '\'s turn.';
        }

        return (
          <div>
            <div className="status">{status}</div>
            <div className="board">
                <div id="firstkalaha">{this.renderKalaha(13)}</div>
                <div id="bowls">
                  <div className="board-row">
                    {this.renderBowl(12)}
                    {this.renderBowl(11)}
                    {this.renderBowl(10)}
                    {this.renderBowl(9)}
                    {this.renderBowl(8)}
                    {this.renderBowl(7)}
                  </div>
                  <div className="board-row">
                    {this.renderBowl(0)}
                    {this.renderBowl(1)}
                    {this.renderBowl(2)}
                    {this.renderBowl(3)}
                    {this.renderBowl(4)}
                    {this.renderBowl(5)}
                  </div>
                </div>
                <div id="secondkalaha">{this.renderKalaha(6)}</div>
            </div>
          </div>
        );
      }
    }

    class RevengeButton extends React.Component {
      handleClick() {
        tryStartGame();
      }

      render() {
        if (gameState.gameStatus.endOfGame) {
          return (
            <div>
              <button className="revengebutton" onClick={() => this.handleClick()}>
                Play again?
              </button>
            </div>
          );
        }
        return (null);
      }
    }

    class EndGameButton extends React.Component {
      handleClick() {
        localStorage.removeItem("MancalaGame");
        setGameState(null);
      }

      render() {
        return (
          <div>
            <button className="endgamebutton" onClick={() => this.handleClick()}>
              End game?
            </button>
          </div>
        );
      }
    }

    return (
        <div>
            <p id="versustext">{gameState.players[0].name} vs {gameState.players[1].name}</p>
            <Board />
            <RevengeButton />
            <EndGameButton />
        </div>
    )
}