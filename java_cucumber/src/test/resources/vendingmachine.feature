Feature: 自動販売機

  Background:
    Given 自動販売機がある
    And "コーラ"を1個追加する
    And "お茶"を1個追加する

  Scenario: 入金額確認
    When 100円を入金
    Then 100円が入金されている

  Scenario: 複数入金額確認
    When 100円を入金
    And  500円を入金
    Then 600円が入金されている

  Scenario: 不正なお金を入金
    When 以下のお金を入金
        |1|
        |5|
        |5000|
        |10000|
    Then 0円が入金されている

  Scenario: お釣りの返却ができる
    Given 100円を入金
    And 500円を入金
    When お釣りを返却
    Then 600円が返却される
    And 0円が入金されている

  Scenario: 在庫が確認できる
    Then "コーラ"の在庫が1個
    And "お茶"の在庫が1個

  Scenario Outline: 購入できる
    Given 500円を入金
    When "<名前>"を購入
    Then "<名前>"の在庫が0個
    And <お釣り>円が入金されている

    Examples:
        |名前|お釣り|
        |コーラ|380|
        |お茶  |350|

