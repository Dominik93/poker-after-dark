import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrationPlayersComponent } from './administration-players.component';

describe('AdministrationPlayersComponent', () => {
  let component: AdministrationPlayersComponent;
  let fixture: ComponentFixture<AdministrationPlayersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrationPlayersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrationPlayersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
