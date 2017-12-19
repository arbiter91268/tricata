package tricata.draw;

import com.surelogic.NonNull;
import com.surelogic.Nullable;
import tricata.model.Tricata;

/**
 * configuration class for a new tricata game
 *
 * @author Kristian Hansen
 */
public final class GameConfiguration {
	@NonNull String name;
	@NonNull int numPlayers;
	@NonNull int numRounds;
	boolean online;
	@Nullable String password;
	@Nullable short port;
	@NonNull Tricata.Mode mode;
}
