/**
 * 
 */
package me.chengtx.java8.io.deleteonexit;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collection;

public final class DeleteOnExitUtil {

	private static final Class<?> DELETE_ON_EXIT_HOOK;
	private static final Collection<?> FILES;

	static {
		try {
			DELETE_ON_EXIT_HOOK = Class.forName("java.io.DeleteOnExitHook",
					true, null);
			final Field field = DELETE_ON_EXIT_HOOK.getDeclaredField("files");
			AccessController.doPrivileged(new PrivilegedAction<Void>() {

				@Override
				public Void run() {
					field.setAccessible(true);
					return null;
				}
			});
			FILES = (Collection<?>) field.get(null);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}

	private DeleteOnExitUtil() {
		// Utility class
	}

	public static void clear() {
		synchronized (DELETE_ON_EXIT_HOOK) {
			FILES.clear();
		}
	}

	public static int size() {
		synchronized (DELETE_ON_EXIT_HOOK) {
			return FILES.size();
		}
	}

	public static Collection<String> get() {
		Collection<String> result = new ArrayList<>();
		synchronized (DELETE_ON_EXIT_HOOK) {
			for (Object obj : FILES) {
				result.add((String) obj);
			}
		}
		return result;
	}
}
