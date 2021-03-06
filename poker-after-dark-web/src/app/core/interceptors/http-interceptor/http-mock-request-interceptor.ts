
import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment.js';
import { tap, catchError } from 'rxjs/operators';

import * as players from '../../mocks/players.json'
import * as config from '../../mocks/config.json'
import * as gamesPages from '../../mocks/games.pages.json'
import * as profit from '../../mocks/profit.json'
import { AdministrationService } from 'src/app/core/services/administration.service.js';
import { ResponseHandler } from './reponse-handler.js';

const urls = [

    {
        urlRegex: environment.baseUrl + 'login',
        method: 'POST',
        jsonProvider: (request) => {
            console.log('login post provider')
            if (request.body.password === 'correct') {
                return {
                    default: {
                        success: true,
                        token: 'random_token'
                    }
                }
            } else {
                return {
                    default: {
                        success: false
                    }
                }
            }
        },
    },
    {
        urlRegex: environment.baseUrl + 'players',
        method: 'OPTIONS',
        jsonProvider: (request) => {
            return players;
        },
    },
    {
        urlRegex: environment.baseUrl + 'players$',
        method: 'GET',
        jsonProvider: (request) => {
            console.log('players get provider')
            return players;
        },
    },
    {
        urlRegex: environment.baseUrl + 'players',
        method: 'POST',
        jsonProvider: (request) => {
            console.log('players post provider')
            var player = {
                "id": Math.random().toString(),
                "name": request.body.playerName,
                "gamesPlayed": 0,
                "liveEarnings": 0,
                "biggestWin": 0,
                "biggestLose": 0
            };
            players.players.push(player);
            return players;
        },
    },
    {
        urlRegex: environment.baseUrl + 'config',
        method: 'GET',
        jsonProvider: (request) => {
            return config;
        }
    },
    {
        urlRegex: environment.baseUrl + 'profit',
        method: 'POST',
        jsonProvider: (request) => {
            console.log('profit post provider')
            if (request.body.playersIds.length > 0) {
                return {
                    default: {
                        profits: profit.profits.
                            filter(profit => request.body.playersIds.indexOf(profit.playerId) > -1)
                    }
                }
            }
            else {
                return profit;
            }
        }
    },
    {
        urlRegex: environment.baseUrl + 'players/.*',
        method: 'GET',
        jsonProvider: (request) => {
            console.log('player/id get provider')
            var splittedUrl = request.url.split('/');
            var playerId = splittedUrl[splittedUrl.length - 1];
            return {
                default: {
                    player: players.players.find(player => player.id === playerId)
                }
            }
        },
    },
    {
        urlRegex: environment.baseUrl + 'games/.*',
        method: 'DELETE',
        jsonProvider: (request) => {
            console.log('games delete provider')
            var splittedUrl = request.url.split('/');
            var gameId = splittedUrl[splittedUrl.length - 1];
            const index = gamesPages.games.findIndex(game => game.id === gameId);
            if (index > -1) {
                gamesPages.games.splice(index, 1);
                profit.profits.forEach(profit => {
                    profit.dataPoints.splice(profit.dataPoints.length - index - 1, 1)
                }
                );
            }
            return {
                default: {
                    success: true
                }
            }
        }
    },
    {
        urlRegex: environment.baseUrl + 'cash-game$',
        method: 'POST',
        jsonProvider: (request) => {
            console.log('games post provider')
            var game = {
                "id": Math.random().toString(),
                "type" : "CASH_GAME",
                "host": {
                    "id": request.body.game.host.id,
                    "name": request.body.game.host.name
                },
                "actions": { "remove": true},
                "date": request.body.game.date.toLocaleString(),
                "pot": request.body.game.pot,
                "participantsCount": request.body.game.participants.length,
                "participants": request.body.game.participants
            };
            gamesPages.games.unshift(game);

            profit.profits.forEach(profit => profit.dataPoints.push(profit.dataPoints[profit.dataPoints.length - 1]))
            game.participants.forEach(participian => {
                var player = players.players.find(player => player.id === participian.playerId);
                player.gamesPlayed++;
                if (player.biggestWin < participian.earnings) {
                    player.biggestWin = participian.earnings;
                }
                if (player.biggestLose > participian.earnings) {
                    player.biggestLose = participian.earnings;
                }
                var pr = profit.profits.find(profit => profit.playerId === participian.playerId);
                pr.dataPoints[pr.dataPoints.length - 1] = (pr.dataPoints[pr.dataPoints.length - 1] + participian.earnings)
            })
            return {
                default: {
                    success: true
                }
            }
        }
    },
    {
        urlRegex: environment.baseUrl + 'tournament$',
        method: 'POST',
        jsonProvider: (request) => {
            console.log('tournament post provider')
            var tournament = {
                "id": Math.random().toString(),
                "type" : "TOURNAMENT",
                "host": {
                    "id": request.body.tournament.host.id,
                    "name": request.body.tournament.host.name
                },
                "actions": { "remove": true},
                "date": request.body.tournament.date.toLocaleString(),
                "pot": request.body.tournament.pot,
                "participantsCount": request.body.tournament.participants.length,
                "participants": request.body.tournament.participants
            };
            gamesPages.games.unshift(tournament);

            profit.profits.forEach(profit => profit.dataPoints.push(profit.dataPoints[profit.dataPoints.length - 1]))
            tournament.participants.forEach(participian => {
                var player = players.players.find(player => player.id === participian.playerId);
                player.gamesPlayed++;
                if (player.biggestWin < participian.earnings) {
                    player.biggestWin = participian.earnings;
                }
                if (player.biggestLose > participian.earnings) {
                    player.biggestLose = participian.earnings;
                }
                var pr = profit.profits.find(profit => profit.playerId === participian.playerId);
                pr.dataPoints[pr.dataPoints.length - 1] = (pr.dataPoints[pr.dataPoints.length - 1] + participian.earnings)
            })
            return {
                default: {
                    success: true
                }
            }
        }
    },
    {
        urlRegex: environment.baseUrl + 'games/pages$',
        method: 'POST',
        jsonProvider: (request) => {
            console.log('games/pages post provider')
            return gamesPages;
        }
    },
];

@Injectable()
export class HttpMockRequestInterceptor implements HttpInterceptor {
    constructor(private injector: Injector,
        private administrationService: AdministrationService,
        private responseHandler: ResponseHandler) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authRequest = request.clone({
            headers: request.headers.set('Authentication', this.administrationService.token)
        });

        for (const element of urls) {
            var regexp = new RegExp(element.urlRegex);
            if (authRequest.method === element.method && regexp.test(authRequest.url)) {
                console.log(authRequest.body)
                console.log('Loaded from json: ' + authRequest.url);
                return of(new HttpResponse({ status: 200, body: ((element.jsonProvider(authRequest)) as any).default })).pipe(
                    tap(evt => {
                        this.responseHandler.handle(evt);
                    }),
                    catchError((err: any) => {
                        return this.responseHandler.handlerError(err);
                    }));
            }
        }
        console.log('Loaded from http call:' + request.url);
        return next.handle(request);
    }

}


