package TestPages;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.E2EFlow;
import listeners.Test_listeners;

@Listeners(Test_listeners.class)
@Test(groups = { "E2EFlow" })
public class E2EFlowTest extends BaseClass {
	@Test(groups = { "E2EFlow" }, description = "Complete E2E Flow: Home → PLP → PDP → MiniCart → Checkout")
	public void Complete_E2E_Flow_Test() throws Throwable {
		try {
			E2EFlow flow = new E2EFlow(driver);
			flow.runCompleteE2EFlow();
		} catch (Exception e) {
			Assert.fail("❌ E2E Test Failed: " + e.getMessage(), e);
		}
	}
}
