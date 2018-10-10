Feature: 自動販売機

  Scenario: 入金額確認
    Given 自動販売機がある
    When 100円を入金
    Then 100円が入金されている
