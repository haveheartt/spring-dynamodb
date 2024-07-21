package com.dynamo.controller;

import com.dynamo.entity.PlayerHistory;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/players")
public class PlayerController {

    private DynamoDbTemplate dynamoDbTemplate;

    public PlayerController(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @PostMapping("/{username}/games")
    public ResponseEntity<Void> save(@PathVariable("username") String username,
                                     @RequestBody ScoreDto scoreDto) {

        var entity = PlayerHistory.fromScore(username, scoreDto);

        dynamoDbTemplate.save(entity);

        return ResponseEntity.ok().build();
    }
}
