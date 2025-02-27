import { Page } from "@playwright/test";

export class AppPage{

    constructor(private page: Page){

    }

    async goToList(): Promise<void>{
        await this.page.goto('http://127.0.0.1:4200/dashboard')
    }
}