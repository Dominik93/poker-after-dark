package com.slusarz.pokerafterdark.domain.validation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationError {

    private static final String PREFIX = "PAD_";

    public static final String MANDATORY_PAGES_FILTER = PREFIX + "MANDATORY_PAGES_FILTER";

    public static final String MANDATORY_ENTRY_FEE = PREFIX + "MANDATORY_ENTRY_FEE";

    public static final String MANDATORY_PAGES_FILTER_FROM = PREFIX + "MANDATORY_PAGES_FILTER_FROM";

    public static final String MANDATORY_PAGES_FILTER_TO = PREFIX + "MANDATORY_PAGES_FILTER_TO";

    public static final String MANDATORY_ENTRY_FEE_MONEY = PREFIX + "MANDATORY_ENTRY_FEE_MONEY";

    public static final String MANDATORY_ENTRY_FEE_CHIPS = PREFIX + "MANDATORY_ENTRY_FEE_CHIPS";

    public static final String NUMBER_OF_PLAYS_BELOW_ZERO = PREFIX + "NUMBER_OF_PLAYS_BELOW_ZERO";

    public static final String MANDATORY_WINNINGS = PREFIX + "MANDATORY_WINNINGS";

    public static final String MANDATORY_LIVE_WINNINGS = PREFIX + "MANDATORY_LIVE_WINNINGS";

    public static final String MANDATORY_NUMBER_OF_PLAYS = PREFIX + "MANDATORY_NUMBER_OF_PLAYS";

    public static final String MANDATORY_PLAYER_NAME = PREFIX + "MANDATORY_PLAYER_NAME";

    public static final String MANDATORY_EARNINGS = PREFIX + "MANDATORY_EARNINGS";

    public static final String MANDATORY_POT = PREFIX + "MANDATORY_POT";

    public static final String MANDATORY_GAME_ID = PREFIX + "MANDATORY_GAME_ID";

    public static final String MANDATORY_PLAYER_ID = PREFIX + "MANDATORY_PLAYER_ID";

    public static final String MANDATORY_HOST_ID = PREFIX + "MANDATORY_HOST_ID";

    public static final String MANDATORY_GAME_DATE = PREFIX + "MANDATORY_GAME_DATE";

    public static final String EMPTY_PARTICIPANTS = PREFIX + "EMPTY_PARTICIPANTS";

    public static final String POT_BELOW_ZERO = PREFIX + "POT_BELOW_ZERO";
}
