package mo.bitcode.kyrie.controller;

import mo.bitcode.kyrie.service.game.GameService;
import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.pool.on_going_game.model.TeamAssertResult;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/game/v1")
public class KyrieUserGameRestController {

    private GameService gameService;

    @GetMapping("/{teamId}")
    public ResponseEntity<Page<Game>> getPagination(@PathVariable("teamId") long teamId,
                                                    @RequestParam int pageRequest) {
        return ResponseEntity.ok(this.gameService.getByTeamId(teamId, pageRequest));
    }

    @PostMapping("/{teamId}/find_game")
    public ResponseEntity<Void> findGame(@PathVariable("teamId") long teamId,
                                         @RequestParam long latitude,
                                         @RequestParam long longitude) {
        this.gameService.findGame(teamId, latitude, longitude);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{teamId}/start_game")
    public ResponseEntity<Void> startGame(@PathVariable("teamId") long teamId) {
        this.gameService.startGame(teamId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/end_game")
    public ResponseEntity<Void> endGame(@RequestBody TeamAssertResult teamAssertResult) {
        this.gameService.endGame(teamAssertResult);
        return ResponseEntity.noContent().build();
    }

}
