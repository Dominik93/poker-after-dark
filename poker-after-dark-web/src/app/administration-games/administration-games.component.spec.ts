import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrationGamesComponent } from './administration-games.component';

describe('AdministrationGamesComponent', () => {
  let component: AdministrationGamesComponent;
  let fixture: ComponentFixture<AdministrationGamesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrationGamesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrationGamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
