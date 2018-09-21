Feature: 自動販売機

  Scenario: 入金学確認
    When 100円を入金
    Then 100円が入金されている

  Scenario: 入金学確認2
    When 100円を入金
    And  500円を入金
    Then 600円が入金されている
