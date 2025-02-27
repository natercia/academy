import {expect, test} from "@playwright/test"
import { AppPage } from "../pages/rack-list.component.page"
import { DsDialogComponent } from "@bmw-ds/components";

test.describe('rack-dashboard', () => {
    test.beforeEach(async({page})=> {
        const appPage = new AppPage(page);
        await appPage.goToList();
    })
    test('should have racks', async ({ page }) =>{
        const rackCard = page.locator('app-rack').first();
        await expect(rackCard).toBeVisible();
    })

    test('should create, view edit and delete rack', async ({ page }) =>{
        const rackCard = page.locator('app-rack').first();
        page.on("dialog", dialog => dialog.accept())
        await expect(rackCard).not.toBeVisible();
        await expect(page.locator('[data-test="add-rack"]').first()).toBeVisible();
        await page.locator('[data-test="add-rack"]').first().click()
        await expect(page.locator('[data-test="form"]').first()).toBeVisible();
        await expect(page.locator('[data-test="add-btn"]').first()).toBeDisabled();
        
        await page.locator('[data-test="form-serial"]').first().fill("PTB11111")
        await page.locator('[data-test="form-team"]').first().fill("1")
        await page.locator('[data-test="form-assettag"]').first().fill("AAA AAA AAAA AAA")
        await page.locator('[data-test="form-location"]').first().fill("Lisboa")
        await expect(page.locator('[data-test="add-btn"]').first()).not.toBeDisabled();
        await page.locator('[data-test="add-btn"]').first().click()
        await page.getByRole('button', { name: 'Dismiss' }).click()

        await expect(rackCard).toBeVisible();

        const responseAwait = page.waitForResponse('**/workstation/racks/*')

        await page.locator('[data-test="details-btn"]').first().click()
        
        const response = await responseAwait;
        const body = await response.json()

        await expect(page.url().toString()).toMatch(`http://127.0.0.1:4200/racks/${body.id}`);

        await expect(page.locator('[data-test="detail-id"]')).toHaveText(`Rack ID: ${body.id.toString()}`)
        await expect(page.locator('[data-test="team-id"]')).toHaveText(`Owning Team's ID: ${body.teamId.toString()}`)
        await expect(page.locator('[data-test="status"]')).toHaveText(`Status: ${body.status}`)
        await expect(page.locator('[data-test="location"]')).toHaveText(`Default Location: ${body.defaultLocation}`)
        await expect(page.locator('[data-test="createdAt"]')).toHaveText(`Created at: ${body.createdAt.replace("-", "/").replace("-", "/").replace("T"," ").split(".")[0]}`)
        //await expect(page.locator('[data-test="modifiedAt"]')).toHaveText(`Last Modified at: ${body.modifiedAt.replace("-", "/").replace("-", "/").replace("T"," ").split(".")[0]}`)

        await expect(page.locator('[data-test="back-btn"]').first()).toBeVisible();
        await page.locator('[data-test="back-btn"]').first().click()

        await expect(rackCard).toBeVisible();
        await expect(page.locator('[data-test="book-btn"]').first()).toBeVisible();
        await expect(page.locator('[data-test="book-btn"]').first().locator('button:text("Book")')).toBeTruthy();
        await expect(page.locator('[data-test="availability"]').first().locator('p:text("available")')).toBeTruthy();
        await page.locator('[data-test="book-btn"]').first().click()
        await expect(page.locator('[data-test="availability"]').first().locator('p:text("unavailable")')).toBeTruthy();
        await expect(page.locator('[data-test="book-btn"]').locator('button:text("Remove Booking")')).toBeTruthy();


        await expect(page.locator('[data-test="rack.delete"]').first()).toBeVisible();
        await page.locator('[data-test="rack.delete"]').first().click();
        await expect(rackCard).not.toBeVisible();

        
        




    })


})
