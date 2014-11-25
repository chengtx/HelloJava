package me.chengtx.java8.io.deleteonexit;

import java.io.File;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class DeleteOnExitUtilTest {
	@Test
	public void testDeleteOnExitUtil() throws Exception {
		int oldSize = DeleteOnExitUtil.size();
		Collection<String> oldContents = DeleteOnExitUtil.get();
		try {
			DeleteOnExitUtil.clear();
			Assert.assertEquals("Now empty", 0, DeleteOnExitUtil.size());
			File tmpFile = File.createTempFile("deleteOnExitUtilTest", "tmp");
			boolean deleted = tmpFile.delete();
			Assert.assertTrue("Tmp file was deleted", deleted);
			tmpFile.deleteOnExit();
			Assert.assertEquals("Now one file", 1, DeleteOnExitUtil.size());
			Assert.assertEquals("File is tmpFile", tmpFile.getPath(),
					DeleteOnExitUtil.get().iterator().next());
			DeleteOnExitUtil.clear();
			Assert.assertEquals("Empty again", 0, DeleteOnExitUtil.size());
		} finally {
			for (String file : oldContents) {
				new File(file).deleteOnExit();
			}
		}
		Assert.assertEquals("Old size restored", oldSize,
				DeleteOnExitUtil.size());
	}
}
